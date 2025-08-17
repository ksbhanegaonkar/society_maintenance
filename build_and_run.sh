#!/bin/bash
set -e

# -------------------------
# Configurations
# -------------------------
PROJECT_ROOT=$(pwd)
UI_DIR="$PROJECT_ROOT/src/webapp/society-maintenance-ui"
SPRING_RESOURCES="$PROJECT_ROOT/src/main/resources/static"
JAR_NAME="society-maintenance-app.jar"
IMAGE_NAME="society-maintenance-app:latest"
APP_PORT=8080

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
BUILT_JAR=$(find target -name "*.jar" | head -n 1)
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
FROM eclipse-temurin:21-jdk-alpine
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
echo "Running container..."
podman run -d -p $APP_PORT:$APP_PORT --name society-maintenance "$IMAGE_NAME"

echo "Done! App is running on port $APP_PORT"
