package com.rioverde.tech.garagemonitor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;

@Slf4j
@SpringBootApplication
public class GaragemonitorApplication implements ApplicationRunner{

	public static void main(String[] args) {

		log.info(Arrays.toString(args));
		for(String arg : args) {
			log.info(arg);
		}
		SpringApplication.run(GaragemonitorApplication.class, args);
	}



	@Override
    public void run (ApplicationArguments args) throws Exception {
	    log.info("Made it");
	    for(String name : args.getOptionNames()) {
            log.info("Option name: " + name);
        }

	    if(args.containsOption("door.image")) {
            log.info("Using: " +args.getOptionValues("door.image"));
        }
    }
}
