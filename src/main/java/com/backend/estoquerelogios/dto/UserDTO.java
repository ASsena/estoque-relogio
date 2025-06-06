package com.backend.estoquerelogios.dto;

import com.backend.estoquerelogios.entities.Role;

public class UserDTO {

    private String username;
    private String password;


    public UserDTO(Long id, String username, String password) {
        this.username = username;
        this.password = password;

    }

    public UserDTO() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}

