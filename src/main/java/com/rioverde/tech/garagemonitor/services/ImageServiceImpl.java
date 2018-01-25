package com.rioverde.tech.garagemonitor.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

@Slf4j
@Component
public class ImageServiceImpl implements ImagesService{

    @Value("${door.image:}")
    String doorImageFile = null;

    @Override
    public byte[] getLatestImage() throws Exception {
        Resource res;
        if(doorImageFile != null && doorImageFile.length() > 0) {
            log.debug("Using image file: " + doorImageFile);
            res = new FileSystemResource(doorImageFile);
        }
        else {
            log.debug("Using default");
            res = new ClassPathResource("static/images/garage_door.jpg");
        }
        return getImageFromResource(res);
    }


    private byte[] getImageFromResource(Resource res) throws Exception {
        InputStream is = res.getInputStream();
        byte[] bytes = new byte[1024];
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        int len = 0;
        while(( len = is.read(bytes)) > 0) {
            bos.write(bytes, 0, len);
        }

        return bos.toByteArray();
    }
}
