package com.congozo.service;

import com.congozo.service.model.CongozoRole;

public class LoginRequest {
    private String username;
    private String password;
    private String email;
    private CongozoRole congozoRole;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public CongozoRole getCongozoRole() {
        return congozoRole;
    }

    public void setCongozoRole(CongozoRole congozoRole) {
        this.congozoRole = congozoRole;
    }
}
