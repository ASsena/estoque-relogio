package com.backend.estoquerelogios.dto;

import jakarta.validation.constraints.NotBlank;

public class CreateUserDTO {

    @NotBlank
    private String username;

    @NotBlank
    private String senha;

    @NotBlank
    private String role;

    public CreateUserDTO() {}

    public CreateUserDTO(String username, String senha, String role) {
        this.username = username;
        this.senha = senha;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

