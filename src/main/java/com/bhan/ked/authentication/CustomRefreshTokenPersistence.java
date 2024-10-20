package com.bhan.ked.authentication;

import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.token.event.RefreshTokenGeneratedEvent;
import io.micronaut.security.token.refresh.RefreshTokenPersistence;
import io.reactivex.rxjava3.core.BackpressureStrategy;
import io.reactivex.rxjava3.core.Flowable;
import jakarta.inject.Singleton;
import org.reactivestreams.Publisher;

import java.util.HashMap;
import java.util.Map;

@Singleton
public class CustomRefreshTokenPersistence implements RefreshTokenPersistence {
    private final Map<String, Authentication> storage = new HashMap<>();

    @Override
    public void persistToken(final RefreshTokenGeneratedEvent event) {
        storage.put(event.getRefreshToken(), event.getAuthentication());
    }

    @Override
    public Publisher<Authentication> getAuthentication(final String refreshToken) {
        return Flowable.create(emitter -> {
            final Authentication authentication = storage.get(refreshToken);
            if ( authentication != null ) {
                emitter.onNext(authentication);
                emitter.onComplete();
            } else {
                emitter.onError(new IllegalArgumentException("refresh token unknown"));
            }
        }, BackpressureStrategy.BUFFER);
    }
}