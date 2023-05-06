package com.example.demo.Model;

public class UserDTO {

    private int id;
    private String username;
    private String password;

    private String nickName;

    public UserDTO() {
    }

    public UserDTO(int id, String username, String password, String nickName) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nickName = nickName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
