package com.bhan.ked;

import io.micronaut.http.annotation.*;

@Controller("/society_maintenance")
public class Society_maintenanceController {

    @Get(uri="/", produces="text/plain")
    public String index() {
        return "Example Response";
    }
}