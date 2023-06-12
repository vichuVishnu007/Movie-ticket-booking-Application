package com.example.demo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;




@SpringBootApplication(exclude = { UserDetailsServiceAutoConfiguration.class , SecurityAutoConfiguration.class})
public class MovieManagementApplication {

//    @Bean
//    public FilterRegistrationBean jwtFilterBean() {
//        FilterRegistrationBean fb = new FilterRegistrationBean();
//        fb.setFilter(new JWTFilter());
//        fb.addUrlPatterns("/api/v1/*");
//        return fb;
//
//    }


    public static void main(String[] args) {
        SpringApplication.run(MovieManagementApplication.class, args);
    }

}
