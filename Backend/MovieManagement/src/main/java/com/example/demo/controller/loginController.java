package com.example.demo.controller;

import com.example.demo.model.UserDTO;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
@RequestMapping("call/consumer")
public class loginController {

    @PostMapping(value = "/login")
    public ResponseEntity<?> performLogin(@RequestBody UserDTO userdto) throws RestClientException, Exception {
        String baseUrl = "http://localhost:8084/auth/v1/login";

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Map<String, String>> result;
        try {
            System.out.println(userdto);
            result = restTemplate.exchange(baseUrl, HttpMethod.POST, getHeaders(userdto), new ParameterizedTypeReference<Map<String, String>>() {
            });
        } catch (Exception e) {
            return new ResponseEntity<String>("Login failed", HttpStatus.UNAUTHORIZED);

        }
        return new ResponseEntity<Map<String, String>>(result.getBody(), HttpStatus.OK);

    }

    private HttpEntity getHeaders(UserDTO userdto) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        return new HttpEntity<UserDTO>(userdto, headers);
    }


}
