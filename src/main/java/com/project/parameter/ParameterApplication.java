package com.project.parameter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.project"})
public class ParameterApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParameterApplication.class, args);
	}
}
