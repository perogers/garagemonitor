package com.rioverde.tech.garagemonitor.services;

import com.rioverde.tech.garagemonitor.domain.SystemInformation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("dev")
public class SystemInfoServiceTest {


    SystemInfoService service;

    @Before
    public void setUp() {
        service = new SystemInfoServiceDevImpl();
    }


    @Test
    public void getSystemInformationTest() {

        SystemInformation systemInformation = service.getSystemInfo();
        Assert.assertNotNull(systemInformation);

    }

}
