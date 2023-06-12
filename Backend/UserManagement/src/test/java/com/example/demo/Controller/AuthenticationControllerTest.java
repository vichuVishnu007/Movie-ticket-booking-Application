package com.example.demo.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.demo.Model.User;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;
import javax.servlet.ServletException;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {AuthenticationController.class})
@ExtendWith(SpringExtension.class)
class AuthenticationControllerTest {
    @Autowired
    private AuthenticationController authenticationController;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private UserService userService;

    /**
     * Method under test: {@link AuthenticationController#generateToken(String, String)}
     */
    @Test
    void testGenerateToken() throws Exception {
        when(userService.loginUser(Mockito.<String>any(), Mockito.<String>any())).thenReturn("Login User");
        authenticationController.generateToken("janedoe", "password123");
        verify(userService).loginUser(Mockito.<String>any(), Mockito.<String>any());
    }

    /**
     * Method under test: {@link AuthenticationController#generateToken(String, String)}
     */
    @Test
    void testGenerateToken2() throws Exception {
        when(userService.loginUser(Mockito.<String>any(), Mockito.<String>any())).thenReturn("invalid");
        assertThrows(ServletException.class, () -> authenticationController.generateToken("janedoe", "password123"));
        verify(userService).loginUser(Mockito.<String>any(), Mockito.<String>any());
    }

    /**
     * Method under test: {@link AuthenticationController#generateToken(String, String)}
     */
    @Test
    void testGenerateToken3() throws Exception {
        when(userService.loginUser(Mockito.<String>any(), Mockito.<String>any())).thenReturn("admin");
        authenticationController.generateToken("janedoe", "password123");
        verify(userService).loginUser(Mockito.<String>any(), Mockito.<String>any());
    }

    /**
     * Method under test: {@link AuthenticationController#generateToken(String, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGenerateToken4() throws Exception {
        

        when(userService.loginUser(Mockito.<String>any(), Mockito.<String>any())).thenReturn(null);
        authenticationController.generateToken("janedoe", "password123");
    }

    /**
     * Method under test: {@link AuthenticationController#generateToken(String, String)}
     */
    @Test
    void testGenerateToken5() throws Exception {
        when(userService.loginUser(Mockito.<String>any(), Mockito.<String>any())).thenReturn("Login User");
        assertThrows(ServletException.class, () -> authenticationController.generateToken(null, "password123"));
    }

    /**
     * Method under test: {@link AuthenticationController#generateToken(String, String)}
     */
    @Test
    void testGenerateToken6() throws Exception {
        when(userService.loginUser(Mockito.<String>any(), Mockito.<String>any())).thenReturn("Login User");
        authenticationController.generateToken("", "password123");
        verify(userService).loginUser(Mockito.<String>any(), Mockito.<String>any());
    }

    /**
     * Method under test: {@link AuthenticationController#generateToken(String, String)}
     */
    @Test
    void testGenerateToken7() throws Exception {
        when(userService.loginUser(Mockito.<String>any(), Mockito.<String>any())).thenReturn("Login User");
        assertThrows(ServletException.class, () -> authenticationController.generateToken("janedoe", null));
    }

    /**
     * Method under test: {@link AuthenticationController#generateToken(String, String)}
     */
    @Test
    void testGenerateToken8() throws Exception {
        when(userService.loginUser(Mockito.<String>any(), Mockito.<String>any())).thenReturn("admin");
        authenticationController.generateToken("", "password123");
        verify(userService).loginUser(Mockito.<String>any(), Mockito.<String>any());
    }

    /**
     * Method under test: {@link AuthenticationController#performLogin(User)}
     */
    @Test
    void testPerformLogin() {

        AuthenticationController authenticationController = new AuthenticationController();

        User user = new User();
        user.setId(1);
        user.setNickName("Nick Name");
        user.setPassword("password123");
        user.setUsername("janedoe");
        ResponseEntity<?> actualPerformLoginResult = authenticationController.performLogin(user);
        assertEquals(2, ((Map<String, String>) actualPerformLoginResult.getBody()).size());
        assertTrue(actualPerformLoginResult.hasBody());
        assertEquals(HttpStatus.CREATED, actualPerformLoginResult.getStatusCode());
        assertTrue(actualPerformLoginResult.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link AuthenticationController#performLogin(User)}
     */
    @Test
    void testPerformLogin2() {

        AuthenticationController authenticationController = new AuthenticationController();

        User user = new User();
        user.setId(1);
        user.setNickName("Nick Name");
        user.setPassword(null);
        user.setUsername(null);
        ResponseEntity<?> actualPerformLoginResult = authenticationController.performLogin(user);
        assertEquals(2, ((Map<String, String>) actualPerformLoginResult.getBody()).size());
        assertTrue(actualPerformLoginResult.hasBody());
        assertEquals(HttpStatus.CREATED, actualPerformLoginResult.getStatusCode());
        assertTrue(actualPerformLoginResult.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link AuthenticationController#performLogin(User)}
     */
    @Test
    void testPerformLogin3() {


        AuthenticationController authenticationController = new AuthenticationController();

        User user = new User();
        user.setId(1);
        user.setNickName("Nick Name");
        user.setPassword(null);
        user.setUsername("foo");
        ResponseEntity<?> actualPerformLoginResult = authenticationController.performLogin(user);
        assertEquals(2, ((Map<String, String>) actualPerformLoginResult.getBody()).size());
        assertTrue(actualPerformLoginResult.hasBody());
        assertEquals(HttpStatus.CREATED, actualPerformLoginResult.getStatusCode());
        assertTrue(actualPerformLoginResult.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link AuthenticationController#performLogin(User)}
     */
    @Test
    void testPerformLogin4() {


        AuthenticationController authenticationController = new AuthenticationController();
        User user = mock(User.class);
        when(user.getPassword()).thenReturn("password123");
        when(user.getUsername()).thenReturn("janedoe");
        doNothing().when(user).setId(anyInt());
        doNothing().when(user).setNickName(Mockito.<String>any());
        doNothing().when(user).setPassword(Mockito.<String>any());
        doNothing().when(user).setUsername(Mockito.<String>any());
        user.setId(1);
        user.setNickName("Nick Name");
        user.setPassword("password123");
        user.setUsername("janedoe");
        ResponseEntity<?> actualPerformLoginResult = authenticationController.performLogin(user);
        assertEquals(2, ((Map<String, String>) actualPerformLoginResult.getBody()).size());
        assertTrue(actualPerformLoginResult.hasBody());
        assertEquals(HttpStatus.CREATED, actualPerformLoginResult.getStatusCode());
        assertTrue(actualPerformLoginResult.getHeaders().isEmpty());
        verify(user, atLeast(1)).getPassword();
        verify(user, atLeast(1)).getUsername();
        verify(user).setId(anyInt());
        verify(user).setNickName(Mockito.<String>any());
        verify(user).setPassword(Mockito.<String>any());
        verify(user).setUsername(Mockito.<String>any());
    }

    /**
     * Method under test: {@link AuthenticationController#forgetPassword(String, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testForgetPassword() {

        (new AuthenticationController()).forgetPassword("Nick Name", "password123");
    }

    /**
     * Method under test: {@link AuthenticationController#forgetPassword(String, String)}
     */
    @Test
    void testForgetPassword2() {

        assertEquals("please enter your nickname & new password ...",
                (new AuthenticationController()).forgetPassword(null, null));
    }

    /**
     * Method under test: {@link AuthenticationController#forgetPassword(String, String)}
     */
    @Test
    void testForgetPassword3() {

        assertEquals("please enter your nickname & new password ...",
                (new AuthenticationController()).forgetPassword("foo", null));
    }

    /**
     * Method under test: {@link AuthenticationController#registerUser(User)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testRegisterUser() throws Exception {


        // Arrange
        // TODO: Populate arranged inputs
        Object[] uriVariables = new Object[]{};
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.post("/auth/v1/addUser", uriVariables)
                .contentType(MediaType.APPLICATION_JSON);

        User user = new User();
        user.setId(1);
        user.setNickName("Nick Name");
        user.setPassword("password123");
        user.setUsername("janedoe");

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult.content(objectMapper.writeValueAsString(user));
        Object[] controllers = new Object[]{authenticationController};
        MockMvc buildResult = MockMvcBuilders.standaloneSetup(controllers).build();

        // Act
        ResultActions actualPerformResult = buildResult.perform(requestBuilder);

    }
}

