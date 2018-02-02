package com.rioverde.tech.garagemonitor.domain;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

@Data
public class HardwareInformation {


    @JsonView(SystemInformation.class)
    private String serial;

    @JsonView(SystemInformation.class)
    private String processor;

    @JsonView(SystemInformation.class)
    private String model_name;

    @JsonView(SystemInformation.class)
    private String cpu_revision;

    @JsonView(SystemInformation.class)
    private String cpu_variant;

    @JsonView(SystemInformation.class)
    private String cpu_architecture;

    @JsonView(SystemInformation.class)
    private float cpu_temperature;

    @JsonView(SystemInformation.class)
    private float cpu_voltage;

    @JsonView(SystemInformation.class)
    private String hardware;

    @JsonView(SystemInformation.class)
    private String cpu_implementer;

    @JsonView(SystemInformation.class)
    private String[] cpu_features;

    @JsonView(SystemInformation.class)
    private long clock_frequency_core;

    @JsonView(SystemInformation.class)
    private long memory_free;

    @JsonView(SystemInformation.class)
    private long memory_used;

    @JsonView(SystemInformation.class)
    private long memory_total;

    @JsonView(SystemInformation.class)
    private long memory_cached;

    @JsonView(SystemInformation.class)
    private long memory_shared;


}
