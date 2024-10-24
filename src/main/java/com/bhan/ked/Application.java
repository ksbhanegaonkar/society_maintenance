package com.bhan.ked;

import io.micronaut.runtime.Micronaut;
import io.micronaut.security.token.generator.RefreshTokenGenerator;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.info.*;
import jakarta.inject.Inject;

@OpenAPIDefinition(
    info = @Info(
            title = "society_maintenance",
            version = "0.0"
    )
)
public class Application {

    public static void main(String[] args) {
        Micronaut.run(Application.class, args);
    }
}