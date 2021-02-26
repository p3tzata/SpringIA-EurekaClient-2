package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class SpringIaEurekaClient2Application {

	public static void main(String[] args) {
		//SpringApplication.run(SpringIaEurekaClient2Application.class, args);
		SpringApplication app = new SpringApplication(SpringIaEurekaClient2Application.class);
        app.setWebApplicationType(WebApplicationType.REACTIVE);
        app.run(args);
	}

}
