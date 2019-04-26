package com.jandi.integration.controller;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @ResponseStatus(value = HttpStatus.OK)
    public String ping() {

        return "pong";
    }
}
