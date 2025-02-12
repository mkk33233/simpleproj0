package com.example.agriculturesystem.dto;


public class LoginResponse {

    private String username;
    private String role;
    private String token;

    // 构造函数
    public LoginResponse(String username , String role, String token) {
        this.username = username;
        this.token = token;
        this.role=role;
    }

    // getter 和 setter 方法
    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
