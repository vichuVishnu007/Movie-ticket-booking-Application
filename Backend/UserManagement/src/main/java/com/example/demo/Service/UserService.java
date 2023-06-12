package com.example.demo.Service;

import java.util.List;

import com.example.demo.Model.User;

public interface UserService 
{
	public User addUser(User user);// user registration
	
	public String loginUser(String username, String password);// login
	
	public List<User> getAllUsers();// will be visible only if you are logged in

	public Integer forgetPassword(String nickName,String newPassword); // to change password if forget
	

}
