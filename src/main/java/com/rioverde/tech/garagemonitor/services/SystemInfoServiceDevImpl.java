package com.rioverde.tech.garagemonitor.services;


import com.rioverde.tech.garagemonitor.domain.HardwareInformation;
import com.rioverde.tech.garagemonitor.domain.PlatformInformation;
import com.rioverde.tech.garagemonitor.domain.SystemInformation;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("dev")
@Service
public class SystemInfoServiceDevImpl implements SystemInfoService {

    @Override
    public SystemInformation getSystemInfo() {
        SystemInformation systemInformation = new SystemInformation();

        PlatformInformation platform = new PlatformInformation();
        platform.setId("platformId");
        platform.setLabel("a label");
        systemInformation.setPlatform(platform);


        HardwareInformation hardware = new HardwareInformation();
        hardware.setCpu_architecture("Pi");
        hardware.setHardware("raspberry pi");
        hardware.setSerial("BR549");
        hardware.setCpu_revision("2.1");
        hardware.setCpu_temperature(5.01F);
        hardware.setCpu_temperature(44.0F);
        systemInformation.setHardware(hardware);




        return systemInformation;
    }
}
