package com.rioverde.tech.garagemonitor.domain;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

@Data
@JsonView
public class SystemInformation {

    private PlatformInformation platform;

    private HardwareInformation hardware;

}
