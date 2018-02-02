package com.rioverde.tech.garagemonitor.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rioverde.tech.garagemonitor.domain.HardwareInformation;
import com.rioverde.tech.garagemonitor.domain.PlatformInformation;
import com.rioverde.tech.garagemonitor.domain.SystemInformation;
import com.rioverde.tech.garagemonitor.services.SystemInfoService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("dev")
public class SystemInformationControllerTests {


    @Mock
    SystemInfoService service;

    SystemInformationController controller;

    MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        controller = new SystemInformationController(service);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void getSystemInformationTest() throws Exception {
        SystemInformation information = new SystemInformation();
        PlatformInformation platformInformation = new PlatformInformation();
        platformInformation.setLabel("a label");
        platformInformation.setId("an id");
        HardwareInformation hardwareInformation = new HardwareInformation();
        hardwareInformation.setHardware("hardware");
        information.setPlatform(platformInformation);
        information.setHardware(hardwareInformation);


        when(service.getSystemInfo()).thenReturn(information);

        MockHttpServletResponse response = mockMvc.perform(get("/system"))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        String json = response.getContentAsString();
        ObjectMapper mapper = new ObjectMapper();
        SystemInformation info = mapper.reader()
                .forType(SystemInformation.class)
                .readValue(json);

        Assert.assertNotNull(info);
        Assert.assertNotNull(info.getPlatform());
        Assert.assertNotNull(info.getHardware());
    }

}
