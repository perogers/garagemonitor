package com.rioverde.tech.garagemonitor.controllers;


import com.rioverde.tech.garagemonitor.services.ImagesService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ImageControllerTests {


    @Mock
    ImagesService service;

    ImagesController controller;

    MockMvc mockMvc;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        controller = new ImagesController(service);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void getImageFromClasspathTest() throws Exception{
        byte [] image = new byte[10000];
        Arrays.fill(image, (byte)0x1);

        when(service.getLatestImage()).thenReturn(image);

        MockHttpServletResponse response = mockMvc.perform(get("/door/image"))
                .andExpect(status().isOk())
                .andExpect(header().string("Cache-Control", "max-age=60"))
                .andReturn().getResponse();

        assertEquals("image/jpeg", response.getContentType());

        byte[] imageBack = response.getContentAsByteArray();

        assertEquals(image.length, imageBack.length);

    }

    @Test
    public void getImageFromFilesystemTest() throws Exception{
        byte [] image = new byte[10000];
        Arrays.fill(image, (byte)0xA);

        when(service.getLatestImage()).thenReturn(image);

        MockHttpServletResponse response = mockMvc.perform(get("/door/image"))
                .andExpect(status().isOk())
                .andExpect(header().string("Cache-Control", "max-age=60"))
                .andReturn().getResponse();

        assertEquals("image/jpeg", response.getContentType());

        byte[] imageBack = response.getContentAsByteArray();

        assertEquals(image.length, imageBack.length);

    }




}
