package com.sk.project.jwt.domain;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	
	
	@RequestMapping({ "/hello" })
	public String firstPage() {
		
		
		return "Hello World";
	}

}