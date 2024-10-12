package com.bhan.ked.service;

import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.utils.SecurityService;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class MaintenanceService {

    @Inject
    private SecurityService securityService;

    public void getMaintenanceDetails(){
    }
}
