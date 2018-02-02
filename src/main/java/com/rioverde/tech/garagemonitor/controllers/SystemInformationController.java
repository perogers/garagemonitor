package com.rioverde.tech.garagemonitor.controllers;

import com.rioverde.tech.garagemonitor.domain.SystemInformation;
import com.rioverde.tech.garagemonitor.services.SystemInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class SystemInformationController {

    @Autowired
    private final SystemInfoService systemInfoService;

    public SystemInformationController(SystemInfoService systemInfoService) {
        this.systemInfoService = systemInfoService;
    }



    @RequestMapping(value = "/system", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public SystemInformation getSystemInformation() {
        return systemInfoService.getSystemInfo();
    }


}
