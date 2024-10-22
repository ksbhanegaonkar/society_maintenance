package com.bhan.ked.authentication;

import com.bhan.ked.entity.UserDetailEntity;
import com.bhan.ked.service.UserDetailService;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.security.authentication.AuthenticationFailureReason;
import io.micronaut.security.authentication.AuthenticationRequest;
import io.micronaut.security.authentication.AuthenticationResponse;
import io.micronaut.security.authentication.UsernamePasswordCredentials;
import io.micronaut.security.authentication.provider.HttpRequestAuthenticationProvider;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.Objects;
import java.util.Optional;

@Singleton
public class AuthenticationProviderUserPassword implements HttpRequestAuthenticationProvider<UsernamePasswordCredentials> {

    @Inject
    private UserDetailService userDetailService;

    @Override
    public @NonNull AuthenticationResponse authenticate(io.micronaut.http.@io.micronaut.core.annotation.Nullable HttpRequest<UsernamePasswordCredentials> httpRequest, @NonNull AuthenticationRequest<String, String> authenticationRequest) {
        Optional<UserDetailEntity> userDetail = userDetailService.getUserDetailByMobileNumber(authenticationRequest.getIdentity());
        return userDetail.isPresent() && authenticationRequest.getSecret().equals(userDetail.get().getPin())
                ? AuthenticationResponse.success(authenticationRequest.getIdentity())
                : AuthenticationResponse.failure(AuthenticationFailureReason.CREDENTIALS_DO_NOT_MATCH);
    }
}