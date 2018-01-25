package com.rioverde.tech.garagemonitor.controllers;

import com.rioverde.tech.garagemonitor.services.ImagesService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.apache.tomcat.util.http.fileupload.IOUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

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
        InputStream is = new ByteArrayInputStream(image);
        IOUtils.copy(is, response.getOutputStream());
    }
}
