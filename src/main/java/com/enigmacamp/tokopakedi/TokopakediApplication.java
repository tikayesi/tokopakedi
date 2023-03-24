package com.enigmacamp.tokopakedi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class TokopakediApplication {

	public static void main(String[] args) {
		SpringApplication.run(TokopakediApplication.class, args);
	}

}
