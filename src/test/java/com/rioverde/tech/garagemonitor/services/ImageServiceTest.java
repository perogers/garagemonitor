package com.rioverde.tech.garagemonitor.services;

import com.rioverde.tech.garagemonitor.camera.Camera;
import com.rioverde.tech.garagemonitor.camera.CameraException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

public class ImageServiceTest {

    @Mock
    private Camera camera;

    private ImagesService service;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        service = new ImageServiceImpl(camera);
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

    @Test
    public void getTakePictureTest() throws Exception {
        byte [] image = new byte[1000];
        Arrays.fill(image, (byte)0xA);
        when(camera.takePicture()).thenReturn(image);

        byte [] pix  = service.takePicture();

        assertArrayEquals(image, pix);
    }


    @Test
    public void getTakePictureCameraFailTest() throws Exception {
        byte [] image = new byte[1000];
        Arrays.fill(image, (byte)0xA);
        when(camera.takePicture()).thenThrow(new CameraException("Test"));

        thrown.expect(CameraException.class);
        byte [] pix  = service.takePicture();

    }
}
