package com.rioverde.tech.garagemonitor.services;

public interface ImagesService {

    byte [] getLatestImage() throws Exception;

    byte [] takePicture();

}
