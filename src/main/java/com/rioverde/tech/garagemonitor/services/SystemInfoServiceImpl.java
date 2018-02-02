package com.rioverde.tech.garagemonitor.services;


import com.pi4j.platform.PlatformManager;
import com.pi4j.system.SystemInfo;
import com.rioverde.tech.garagemonitor.domain.HardwareInformation;
import com.rioverde.tech.garagemonitor.domain.PlatformInformation;
import com.rioverde.tech.garagemonitor.domain.SystemInformation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("!dev")
@Slf4j
@Service
public class SystemInfoServiceImpl implements SystemInfoService {

    @Override
    public SystemInformation getSystemInfo() {
        log.debug("Entering");
        SystemInformation system = new SystemInformation();

        PlatformInformation platform = new PlatformInformation();
        platform.setId(PlatformManager.getPlatform().getId());
        platform.setLabel(PlatformManager.getPlatform().getLabel());
        system.setPlatform(platform);

        try {
            HardwareInformation hardware = new HardwareInformation();
            hardware.setSerial(SystemInfo.getSerial());
            hardware.setModel_name(SystemInfo.getModelName());
            hardware.setProcessor(SystemInfo.getProcessor());
            hardware.setHardware(SystemInfo.getHardware());
            hardware.setCpu_features(SystemInfo.getCpuFeatures());
            hardware.setCpu_implementer(SystemInfo.getCpuImplementer());
            hardware.setCpu_variant(SystemInfo.getCpuVariant());
            hardware.setCpu_temperature(SystemInfo.getCpuTemperature());
            hardware.setCpu_revision(SystemInfo.getCpuRevision());
            hardware.setCpu_architecture(SystemInfo.getCpuArchitecture());
            hardware.setCpu_voltage(SystemInfo.getCpuVoltage());
            hardware.setClock_frequency_core(SystemInfo.getClockFrequencyCore());

            hardware.setMemory_cached(SystemInfo.getMemoryCached());
            hardware.setMemory_free(SystemInfo.getMemoryFree());
            hardware.setMemory_shared(SystemInfo.getMemoryShared());
            hardware.setMemory_used(SystemInfo.getMemoryUsed());
            hardware.setMemory_total(SystemInfo.getMemoryTotal());

            system.setHardware(hardware);
        }
        catch (Exception e) {
            log.error("Got exception: " + e.getMessage(), e);
            throw new RuntimeException(e);
        }
        return system;
    }
}
