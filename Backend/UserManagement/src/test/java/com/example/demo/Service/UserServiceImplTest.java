package com.example.demo.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.demo.Model.User;
import com.example.demo.Repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UserServiceImpl.class})
@ExtendWith(SpringExtension.class)
class UserServiceImplTest {
    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserServiceImpl userServiceImpl;

    /**
     * Method under test: {@link UserServiceImpl#addUser(User)}
     */
    @Test
    void testAddUser() {
        User user = new User();
        user.setId(1);
        user.setNickName("Nick Name");
        user.setPassword("password123");
        user.setUsername("janedoe");
        when(userRepository.saveAndFlush(Mockito.<User>any())).thenReturn(user);

        User user2 = new User();
        user2.setId(1);
        user2.setNickName("Nick Name");
        user2.setPassword("password123");
        user2.setUsername("janedoe");
        assertSame(user, userServiceImpl.addUser(user2));
        verify(userRepository).saveAndFlush(Mockito.<User>any());
    }

    /**
     * Method under test: {@link UserServiceImpl#loginUser(String, String)}
     */
    @Test
    void testLoginUser() {
        User user = new User();
        user.setId(1);
        user.setNickName("Nick Name");
        user.setPassword("password123");
        user.setUsername("janedoe");
        when(userRepository.validateUser(Mockito.<String>any(), Mockito.<String>any())).thenReturn(user);
        assertEquals("user", userServiceImpl.loginUser("janedoe", "password123"));
        verify(userRepository).validateUser(Mockito.<String>any(), Mockito.<String>any());
    }

    /**
     * Method under test: {@link UserServiceImpl#loginUser(String, String)}
     */
    @Test
    void testLoginUser2() {
        User user = new User();
        user.setId(1);
        user.setNickName("Nick Name");
        user.setPassword("password123");
        user.setUsername("janedoe");
        when(userRepository.validateUser(Mockito.<String>any(), Mockito.<String>any())).thenReturn(user);
        assertEquals("user", userServiceImpl.loginUser("admin", "password123"));
        verify(userRepository).validateUser(Mockito.<String>any(), Mockito.<String>any());
    }

    /**
     * Method under test: {@link UserServiceImpl#loginUser(String, String)}
     */
    @Test
    void testLoginUser3() {

        User user = new User();
        user.setId(1);
        user.setNickName("Nick Name");
        user.setPassword("password123");
        user.setUsername("janedoe");
        when(userRepository.validateUser(Mockito.<String>any(), Mockito.<String>any())).thenReturn(user);
        userServiceImpl.loginUser(null, "password123");
    }

    /**
     * Method under test: {@link UserServiceImpl#loginUser(String, String)}
     */
    @Test
    void testLoginUser4() {
        User user = new User();
        user.setId(1);
        user.setNickName("Nick Name");
        user.setPassword("password123");
        user.setUsername("janedoe");
        when(userRepository.validateUser(Mockito.<String>any(), Mockito.<String>any())).thenReturn(user);
        assertEquals("admin", userServiceImpl.loginUser("admin", "admin123"));
    }

    /**
     * Method under test: {@link UserServiceImpl#loginUser(String, String)}
     */
    @Test
    void testLoginUser5() {

        User user = new User();
        user.setId(1);
        user.setNickName("Nick Name");
        user.setPassword("password123");
        user.setUsername("janedoe");
        when(userRepository.validateUser(Mockito.<String>any(), Mockito.<String>any())).thenReturn(user);
        userServiceImpl.loginUser("admin", null);
    }

    /**
     * Method under test: {@link UserServiceImpl#getAllUsers()}
     */
    @Test
    void testGetAllUsers() {
        when(userRepository.findAll()).thenReturn(new ArrayList<>());
        assertNull(userServiceImpl.getAllUsers());
        verify(userRepository).findAll();
    }

    /**
     * Method under test: {@link UserServiceImpl#getAllUsers()}
     */
    @Test
    void testGetAllUsers2() {
        User user = new User();
        user.setId(1);
        user.setNickName("Nick Name");
        user.setPassword("password123");
        user.setUsername("janedoe");

        ArrayList<User> userList = new ArrayList<>();
        userList.add(user);
        when(userRepository.findAll()).thenReturn(userList);
        List<User> actualAllUsers = userServiceImpl.getAllUsers();
        assertSame(userList, actualAllUsers);
        assertEquals(1, actualAllUsers.size());
        verify(userRepository).findAll();
    }

    /**
     * Method under test: {@link UserServiceImpl#forgetPassword(String, String)}
     */
    @Test
    void testForgetPassword() {
        when(userRepository.forgetPassword(Mockito.<String>any(), Mockito.<String>any())).thenReturn(1);
        assertEquals(1, userServiceImpl.forgetPassword("Nick Name", "password123").intValue());
        verify(userRepository).forgetPassword(Mockito.<String>any(), Mockito.<String>any());
    }
}

