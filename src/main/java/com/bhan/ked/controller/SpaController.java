package com.bhan.ked.controller;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;

import java.io.File;

@Controller
public class SpaController {

    @Get(value = "/{path:.*}", produces = MediaType.TEXT_HTML)
    public HttpResponse<?> index() {
        return HttpResponse.ok()
                .contentType(MediaType.TEXT_HTML)
                .body(new File("src/main/resources/static/index.html"));
    }
}