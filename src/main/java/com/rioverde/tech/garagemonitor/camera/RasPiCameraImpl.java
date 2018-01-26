package com.rioverde.tech.garagemonitor.camera;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.*;

@Slf4j
@Component
public class RasPiCameraImpl implements Camera {

    private static final String RAS_PI_STILL = "/usr/bin/raspistill";
    private static final String DELAY_MS = "-t";
    private static final String IMAGE_DESTINATION = "-o";
    private static final String IMAGE_DESTINATION_STD_OUT = "-";
    private static final String IMAGE_ENCODING = "-e";
    private static final String EXPOSURE_MODE = "-ex";
    private static final String JPEG_QUALITY = "-q";


    @Override
    public byte [] takePicture() {
        log.debug("Starting taking picture");
        ProcessBuilder processBuilder =
                new ProcessBuilder(RAS_PI_STILL,
                        DELAY_MS, "1",
                        IMAGE_DESTINATION, IMAGE_DESTINATION_STD_OUT,
                        IMAGE_ENCODING, "jpg",
                        EXPOSURE_MODE, "auto",
                        JPEG_QUALITY, "75");

        byte [] image;
        try {

            Process process = processBuilder.start();
            log.debug("Started Process, wait for ending");
            ByteStreamReader bsr = new ByteStreamReader(process.getInputStream());
            bsr.start();
            int exitValue = process.waitFor();
            if( exitValue != 0 ) {
                log.error(getCameraErrors(process.getErrorStream()));
                throw new CameraException("Error Occurred");
            }
            bsr.join();
            image = bsr.getBytes();
            process.destroy();
        }
        catch (IOException ex ){
            log.error("IO Failure: " + ex.getMessage(), ex);
            throw new CameraException(ex.getMessage(), ex);
        }
        catch (InterruptedException ex ){
            log.error("Interrupted Failure: " + ex.getMessage(), ex);
            throw new CameraException(ex.getMessage(), ex);
        }
        catch (Exception ex ){
            log.error("General Failure: " + ex.getMessage(), ex);
            throw new CameraException(ex.getMessage(), ex);
        }
        return image;
    }

    private static String getCameraErrors(InputStream errStream) {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(errStream));
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line + System.getProperty("line.separator"));
            }
        }
        catch (IOException e) {
            log.error(e.getMessage());
        } finally {
            try{ br.close();} catch (Throwable ignore){}
        }
        return sb.toString();
    }
}

@Slf4j
class ByteStreamReader extends Thread {
    private InputStream is;
    private byte [] bytes;


    ByteStreamReader(InputStream is) {
        this.is = is;
    }

    public void run() {
        try {
            byte[] bytes = new byte[1024];
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            int len = 0;

            log.debug("Starting reading...");
            while(( len = is.read(bytes)) > 0) {
                bos.write(bytes, 0, len);
            }
            log.debug("Completed reading");
            this.bytes = bos.toByteArray();

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public byte [] getBytes() {
        return this.bytes;
    }
}
