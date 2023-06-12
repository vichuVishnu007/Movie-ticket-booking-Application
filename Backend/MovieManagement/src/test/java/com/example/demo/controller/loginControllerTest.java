package com.example.demo.controller;

import com.example.demo.model.UserDTO;
import com.example.demo.service.DataPublisherServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {loginController.class})
@ExtendWith(SpringExtension.class)
class loginControllerTest {
    @MockBean
    private DataPublisherServiceImpl dataPublisherServiceImpl;

    @MockBean
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private loginController loginController;

    /**
     * Method under test: {@link loginController#performLogin(UserDTO)}
     */
    @Test
    void testPerformLogin() throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setPassword("password123");
        userDTO.setUsername("janedoe");
        String content = (new ObjectMapper()).writeValueAsString(userDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/call/consumer/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(loginController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(401))
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Login failed"));
    }

    /**
     * Method under test: {@link loginController#performLogin(UserDTO)}
     */
    @Test
    void testPerformLogin2() throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setPassword("password123");
        userDTO.setUsername("janedoe");
        String content = (new ObjectMapper()).writeValueAsString(userDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/call/consumer/login", "Uri Variables")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(loginController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(401))
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Login failed"));
    }
}

