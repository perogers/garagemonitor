package com.rioverde.tech.garagemonitor.services;

import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.assertNotNull;

public class ImageServiceTest {


    private ImagesService service;

    @Before
    public void setUp() {
        service = new ImageServiceImpl();
    }

    @Test
    public void getImageDefaultTest() throws  Exception{

        byte [] image = service.getLatestImage();
        assertNotNull(image);
    }


    @Test
    public void getImageForGivenTest() throws  Exception{

        ((ImageServiceImpl)service).doorImageFile = "./target/classes/static/images/garage_door.jpg";

        byte [] image = service.getLatestImage();
        assertNotNull(image);
    }
}
