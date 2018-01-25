package com.rioverde.tech.garagemonitor.services;

import com.rioverde.tech.garagemonitor.domain.Temperature;
import com.rioverde.tech.garagemonitor.domain.TemperatureScale;
import org.springframework.stereotype.Component;

@Component
public class TemperatureServiceImpl implements TemperatureService {

    @Override
    public Temperature getTemperature() {
        Temperature temperature = new Temperature();
        temperature.setTemperature(75.2);
        temperature.setScale(TemperatureScale.Fahrenheit);
        return temperature;
    }
}
