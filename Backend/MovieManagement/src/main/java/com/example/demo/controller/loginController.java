package com.example.demo.controller;

import com.example.demo.model.UserDTO;
import com.example.demo.service.DataPublisherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
@RequestMapping("call/consumer")
@CrossOrigin("*")
public class loginController {

    @Autowired
    private DataPublisherServiceImpl dataPublisherService;

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    @PostMapping(value = "/login")

    public ResponseEntity<?> performLogin(@RequestBody UserDTO userdto) throws RestClientException, Exception {
        String baseUrl = "http://3.93.78.221:8084/auth/v1/login";
        String msg= "AuthenticationRequired";

        RestTemplate restTemplate = new RestTemplate();



        ResponseEntity<Map<String, String>> result;
        try {
            System.out.println(userdto);
            result = restTemplate.exchange(baseUrl, HttpMethod.POST, getHeaders(userdto), new ParameterizedTypeReference<Map<String, String>>() {
            });
//            kafkaTemplate.send("TopicUser","Auth needed");
//            dataPublisherService.setTemp(msg);
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
