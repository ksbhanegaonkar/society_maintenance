package com.bhan.ked.authentication;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.security.authentication.AuthenticationFailureReason;
import io.micronaut.security.authentication.AuthenticationRequest;
import io.micronaut.security.authentication.AuthenticationResponse;
import io.micronaut.security.authentication.UsernamePasswordCredentials;
import io.micronaut.security.authentication.provider.HttpRequestAuthenticationProvider;
import jakarta.inject.Singleton;

@Singleton
public class AuthenticationProviderUserPassword implements HttpRequestAuthenticationProvider<UsernamePasswordCredentials> {
    @Override
    public @NonNull AuthenticationResponse authenticate(io.micronaut.http.@io.micronaut.core.annotation.Nullable HttpRequest<UsernamePasswordCredentials> httpRequest, @NonNull AuthenticationRequest<String, String> authenticationRequest) {
            return authenticationRequest.getIdentity().equals("test") && authenticationRequest.getSecret().equals("test")
                ? AuthenticationResponse.success(authenticationRequest.getIdentity())
                : AuthenticationResponse.failure(AuthenticationFailureReason.CREDENTIALS_DO_NOT_MATCH);
    }
}