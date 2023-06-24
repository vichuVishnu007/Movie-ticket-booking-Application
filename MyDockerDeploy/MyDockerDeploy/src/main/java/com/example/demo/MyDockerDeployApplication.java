package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class MyDockerDeployApplication {

	@GetMapping("/")
	public String myDisplay()
	{
		return "This is docker demo";
	}
	
	public static void main(String[] args) {
		SpringApplication.run(MyDockerDeployApplication.class, args);
	}

}
