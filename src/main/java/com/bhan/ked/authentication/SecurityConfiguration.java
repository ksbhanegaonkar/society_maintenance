package com.bhan.ked.authentication;

import io.micronaut.context.annotation.Requires;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.filters.SecurityFilter;
import jakarta.inject.Singleton;

@Singleton
@Requires(beans = SecurityFilter.class)
public class SecurityConfiguration {
    @Secured("permitAll")
    public void permitSwagger() {
    }
}
