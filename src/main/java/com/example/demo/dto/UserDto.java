package com.example.demo.dto;

import java.util.List;

public class UserDto {
    private String name;
    private String password;
    private List<String> auths;

    public UserDto(String name, String password, List<String> auths) {
        this.name = name;
        this.password = password;
        this.auths = auths;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getAuths() {
        return auths;
    }

    public void setAuths(List<String> auths) {
        this.auths = auths;
    }
}
