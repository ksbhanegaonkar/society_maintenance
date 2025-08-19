#!/bin/bash
set -e

# -------------------------
# Configurations
# -------------------------
PROJECT_ROOT=$(pwd)
UI_DIR="$PROJECT_ROOT/src/webapp/society-maintenance-ui"
SPRING_RESOURCES="$PROJECT_ROOT/src/main/resources/static"
JAR_NAME="society-maintenance.jar"
IMAGE_NAME="society-maintenance-app:latest"
CONTAINER_NAME="society-maintenance"
APP_PORT=8090

# -------------------------
# Step 0: Cleanup old container
# -------------------------
echo "Checking for existing container..."
if podman ps -a --format '{{.Names}}' | grep -Eq "^${CONTAINER_NAME}\$"; then
  echo "Stopping and removing existing container: $CONTAINER_NAME"
  podman stop "$CONTAINER_NAME" || true
  podman rm -f "$CONTAINER_NAME" || true
fi

# -------------------------
# Step 1: Build React UI
# -------------------------
echo "Building React UI..."
cd "$UI_DIR"
npm install
npm run build

# Clear existing static files
echo "Copying React build to Spring Boot resources..."
rm -rf "$SPRING_RESOURCES"/*
cp -r build/* "$SPRING_RESOURCES"

# -------------------------
# Step 2: Build Spring Boot App
# -------------------------
echo "Building Spring Boot JAR..."
cd "$PROJECT_ROOT"
./mvnw clean package -DskipTests

# Find the generated JAR
BUILT_JAR=$(find target -maxdepth 1 -type f -name "society_maintenance-*.jar" ! -name "*-plain.jar" | head -n 1)
if [[ -z "$BUILT_JAR" ]]; then
  echo "ERROR: JAR not found!"
  exit 1
fi

# Copy to standard name
cp "$BUILT_JAR" "$PROJECT_ROOT/$JAR_NAME"

# -------------------------
# Step 3: Create Dockerfile
# -------------------------
echo "Creating temporary Dockerfile..."
DOCKERFILE="$PROJECT_ROOT/Dockerfile.temp"
cat > "$DOCKERFILE" <<EOL
FROM docker.io/eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY $JAR_NAME app.jar
EXPOSE $APP_PORT
ENTRYPOINT ["java","-jar","app.jar"]
EOL

# -------------------------
# Step 4: Build Podman Image
# -------------------------
echo "Building Podman image..."
podman build -f "$DOCKERFILE" -t "$IMAGE_NAME" .

# Cleanup Dockerfile and JAR
rm "$DOCKERFILE"
rm "$JAR_NAME"

# -------------------------
# Step 5: Run Container
# -------------------------
echo "Running new container..."
podman run -d -p $APP_PORT:$APP_PORT --name "$CONTAINER_NAME" "$IMAGE_NAME"

echo "Done! App is running on port $APP_PORT"
