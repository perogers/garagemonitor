package com.rioverde.tech.garagemonitor.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rioverde.tech.garagemonitor.domain.Temperature;
import com.rioverde.tech.garagemonitor.domain.TemperatureScale;
import org.junit.Test;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;

public class TemperatureTests {

    @Test
    public void serializeTest() throws JsonProcessingException {
        Temperature temperature = new Temperature();
        temperature.setScale(TemperatureScale.Fahrenheit);
        temperature.setTemperature(99.1);


        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(MapperFeature.DEFAULT_VIEW_INCLUSION);

        String result = mapper
                .writeValueAsString(temperature);

        assertThat(result, containsString("Fahrenheit"));
        assertThat(result, containsString("scale"));
        assertThat(result, containsString("temperature"));
        assertThat(result, containsString("99.1"));
    }
}
