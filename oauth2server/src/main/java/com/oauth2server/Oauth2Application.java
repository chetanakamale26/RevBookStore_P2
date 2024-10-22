package com.oauth2server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class Oauth2Application extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(Oauth2Application.class, args);
	}

}
