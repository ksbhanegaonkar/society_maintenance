package com.bhan.ked.controller;

import com.bhan.ked.service.MaintenanceService;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import jakarta.inject.Inject;

import java.security.Principal;

@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller
public class HomeController {

    @Inject
    private MaintenanceService maintenanceService;

    @Produces(MediaType.TEXT_PLAIN)
    @Get
    public String index(Principal principal) {
        return principal.getName();
    }

    @Produces(MediaType.TEXT_PLAIN)
    @Get(uri = "/hello")
    public String hello(Principal principal){
        maintenanceService.getMaintenanceDetails();
        return "hello";
    }
}