package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import com.example.demo.filter.JWTFilter;

@SpringBootApplication
@EnableAutoConfiguration
public class MovieManagementApplication {
	
	@Bean
	public FilterRegistrationBean jwtFilterBean()
	{
		FilterRegistrationBean fb = new FilterRegistrationBean();
		fb.setFilter(new JWTFilter());
		fb.addUrlPatterns("/api/v1/*");
		return fb;
		
	}

	

	public static void main(String[] args) {
		SpringApplication.run(MovieManagementApplication.class, args);
	}

}
