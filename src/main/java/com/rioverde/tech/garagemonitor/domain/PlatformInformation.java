package com.rioverde.tech.garagemonitor.domain;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

@Data
public class PlatformInformation {

    @JsonView(SystemInformation.class)
    private String label;

    @JsonView(SystemInformation.class)
    private String id;
}
