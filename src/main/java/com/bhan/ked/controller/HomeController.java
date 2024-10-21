package com.bhan.ked.controller;

import com.bhan.ked.model.MaintenanceDetail;
import com.bhan.ked.service.MaintenanceService;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
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
        return "hello";
    }

    @Produces(MediaType.TEXT_PLAIN)
    @Post(uri = "/saveMaintenance")
    public String saveMaintenanceDetail(@Body MaintenanceDetail maintenanceDetail){
        maintenanceService.saveMaintenanceDetail(maintenanceDetail);
        return "hello";
    }

}