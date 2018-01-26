package com.rioverde.tech.garagemonitor.controllers;

import com.rioverde.tech.garagemonitor.services.ImagesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.apache.tomcat.util.http.fileupload.IOUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Slf4j
@Controller
public class ImagesController {

    private final ImagesService service;

    public ImagesController(ImagesService service) {
        this.service = service;
    }


    @GetMapping({"/door/image"})
    public void getImage(HttpServletResponse response) throws IOException, Exception {

        byte[] image = service.getLatestImage();

        response.setContentType("image/jpeg");
        response.setContentLength(image.length);
        InputStream is = new ByteArrayInputStream(image);
        IOUtils.copy(is, response.getOutputStream());
    }

    @GetMapping({"/door/camera"})
    public void getCamera(HttpServletResponse response) throws IOException, Exception {
        log.debug("Calling");
        byte[] image = service.takePicture();
        log.debug("Got image");
        response.setContentType("image/jpeg");
        response.setContentLength(image.length);
        InputStream is = new ByteArrayInputStream(image);
        IOUtils.copy(is, response.getOutputStream());
    }

}
