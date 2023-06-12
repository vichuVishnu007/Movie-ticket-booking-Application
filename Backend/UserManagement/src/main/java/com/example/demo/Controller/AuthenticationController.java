package com.example.demo.Controller;

import com.example.demo.Model.User;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin("*")
@RequestMapping("auth/v1")
@RestController
public class AuthenticationController {

    private Map<String, String> mapObj = new HashMap<String, String>();

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/addUser")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        if (userService.addUser(user) != null) {
            return new ResponseEntity<User>(user, HttpStatus.CREATED);
        }
        return new ResponseEntity<String>("User not registered", HttpStatus.CONFLICT);
    }


    public String generateToken(String username, String password) throws ServletException, Exception {
        String jwtToken;

        if (username == null || password == null) {
            throw new ServletException("Please enter valid username and password");
        }

        String flag = userService.loginUser(username, password);

        if (flag.equals("invalid")) {
            throw new ServletException("Invalid credentials");

        } else if (flag.equals("admin")) {
            jwtToken = Jwts.builder().setSubject(username).claim("role","admin").setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + 3000000))
                    .signWith(SignatureAlgorithm.HS256, "secret key").compact();
        } else {
            jwtToken = Jwts.builder().setSubject(username).claim("role","user").setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + 3000000))
                    .signWith(SignatureAlgorithm.HS256, "secret key").compact();
        }

        return jwtToken;
    }

    @PostMapping("/login")
    public ResponseEntity<?> performLogin(@RequestBody User user) {
        try {
            System.out.println(user.getUsername() + user.getPassword());
            String jwtToken = generateToken(user.getUsername(), user.getPassword());
            if (user.getUsername().equals("admin") & user.getPassword().equals("admin123")) {
                mapObj.put("message", "Admin successfully logged in");
                mapObj.put("jwtToken", jwtToken);
            } else {
                mapObj.put("message", "User successfully logged in");
                mapObj.put("jwtToken", jwtToken);
            }

        } catch (Exception e) {
            mapObj.put("message", "User not logged in");
            mapObj.put("jwtToken", null);
        }

        return new ResponseEntity<>(mapObj, HttpStatus.CREATED);
    }

    @PutMapping("/forgetPassword/{nickName}/{newPassword}")
    public String forgetPassword(@PathVariable String nickName, @PathVariable String newPassword) {
        if (nickName != null && newPassword != null) {
            int response = userService.forgetPassword(nickName, newPassword);
            if (response == 1) {
                return "password updated successfully...";
            } else {
                return "password updation failed...";
            }
        }
        return "please enter your nickname & new password ...";
    }

//    @KafkaListener(topics = "KafkaUser", groupId = "group-id")
//    public void listen(String message) {
//        System.out.println("Received Messasge in group - group-id: " + message);
//    }
}















