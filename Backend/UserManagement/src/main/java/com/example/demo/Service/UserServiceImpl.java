package com.example.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.Model.User;
import com.example.demo.Repository.UserRepository;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepo;

    private User user;



    @Override
    public User addUser(User user) {
        if (user != null) {
//            User user1 = new User();
//            String encodedPassword= new BCryptPasswordEncoder().encode(user.getPassword());
//            user1.setId(user.getId());
//            user1.setUsername(user.getUsername());
//            user1.setPassword(encodedPassword);
//            user1.setNickName(user.getNickName());
            return userRepo.saveAndFlush(user);
        }
        return null;
    }

    @Override
    public String loginUser(String username, String password) {
        if (username.equals("admin") && password.equals("admin123")) {
            return "admin";
        } else {
            User user1 = userRepo.validateUser(username, password);
            System.out.println("User: " + user1.getUsername());
            if (user1 != null) {
                return "user";
            }
        }
        return "invalid";
    }

    @Override
    public List<User> getAllUsers() {

        List<User> userList = userRepo.findAll();

        if (userList != null & userList.size() > 0) {
            return userList;
        } else
            return null;
    }

    @Override
    public Integer forgetPassword(String nickName,String newPassword) {

        int response=userRepo.forgetPassword(nickName,newPassword);
        return response;
    }


}














