package com.sk.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Application {
	@RequestMapping("/")
	public String home() {
		return "Hello Docker World.test";
	}

	@RequestMapping("/sample-gs-spring-boot-docker")
	public String home2() {
		return "Hello ucking there.";
	}

	@RequestMapping("/sample")
	public String home3() {
		return "Hello ru sober?";
	}
	
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
