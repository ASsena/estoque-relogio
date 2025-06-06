package com.backend.estoquerelogios.dto;

import com.backend.estoquerelogios.entities.Role;
import jakarta.validation.constraints.NotBlank;

public class CreateUserDTO {

    @NotBlank
    private String username;

    @NotBlank
    private String senha;

    @NotBlank
    private Role role;

    public CreateUserDTO() {}

    public CreateUserDTO(String username, String senha, Role role) {
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

    public @NotBlank Role getRole() {
        return role;
    }

    public void setRole(@NotBlank Role role) {
        this.role = role;
    }
}

