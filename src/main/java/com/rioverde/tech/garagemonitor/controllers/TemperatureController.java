package com.rioverde.tech.garagemonitor.controllers;

import com.rioverde.tech.garagemonitor.domain.Temperature;
import com.rioverde.tech.garagemonitor.services.TemperatureService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;


@RestController
public class TemperatureController {

    private final TemperatureService temperatureService;

    public TemperatureController(TemperatureService temperatureService) {
        this.temperatureService = temperatureService;
    }


    @GetMapping({"/", "/garage/temperature"})
    public Temperature getTemperature(HttpServletResponse response) {
         return temperatureService.getTemperature();
    }


}
