package com.rioverde.tech.garagemonitor.controllers;

import com.rioverde.tech.garagemonitor.domain.Temperature;
import com.rioverde.tech.garagemonitor.domain.TemperatureScale;
import com.rioverde.tech.garagemonitor.services.TemperatureService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TemperatureControllerTests {

    @Mock
    TemperatureService service;

    TemperatureController controller;

    MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        controller = new TemperatureController(service);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void getTemperatureTest() throws Exception {
        Temperature temperature = new Temperature();
        temperature.setScale(TemperatureScale.Fahrenheit);
        temperature.setTemperature(89.2);

        when(service.getTemperature()).thenReturn(temperature);

        MockHttpServletResponse response = mockMvc.perform(get("/garage/temperature" ))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        String content = response.getContentAsString();

        assertNotNull(content);



    }


}
