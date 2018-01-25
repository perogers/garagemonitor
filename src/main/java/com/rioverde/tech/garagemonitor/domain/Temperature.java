package com.rioverde.tech.garagemonitor.domain;


import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

@Data
public class Temperature {

    @JsonView
    private double temperature;

    @JsonView
    private TemperatureScale scale;


}
