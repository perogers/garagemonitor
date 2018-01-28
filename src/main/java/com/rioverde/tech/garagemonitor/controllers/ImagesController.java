package com.rioverde.tech.garagemonitor.controllers;

import com.rioverde.tech.garagemonitor.services.ImagesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.CacheControl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Slf4j
@Controller
public class ImagesController {

    private final ImagesService service;

    public ImagesController(ImagesService service) {
        this.service = service;
    }


    @GetMapping({"/door/image"})
    public ResponseEntity<byte[]> getImage() throws IOException, Exception {

        byte[] image = service.getLatestImage();

        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(60, TimeUnit.SECONDS))
                .contentType(MediaType.IMAGE_JPEG)
                .body(image);
    }

    @GetMapping({"/door/camera"})
    public ResponseEntity<byte[]> getCameraImage(HttpServletResponse response) throws IOException, Exception {
        log.debug("Calling");
        byte[] image = service.takePicture();
        log.debug("Got image");
        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(60, TimeUnit.SECONDS))
                .contentType(MediaType.IMAGE_JPEG)
                .body(image);
    }

}
