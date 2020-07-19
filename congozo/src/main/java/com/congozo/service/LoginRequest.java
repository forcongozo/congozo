package com.congozo.service;

import com.congozo.service.model.CongozoRole;

public class LoginRequest {
    private String usernameOrEmail;
    private String password;
    private CongozoRole congozoRole;

    public String getUsernameOrEmail() {
        return usernameOrEmail;
    }

    public void setUsernameOrEmail(String usernameOrEmail) {
        this.usernameOrEmail = usernameOrEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public CongozoRole getCongozoRole() {
        return congozoRole;
    }

    public void setCongozoRole(CongozoRole congozoRole) {
        this.congozoRole = congozoRole;
    }
}
