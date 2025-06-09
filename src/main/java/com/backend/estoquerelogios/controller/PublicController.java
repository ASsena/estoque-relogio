package com.backend.estoquerelogios.controller;

import com.backend.estoquerelogios.dto.CreateUserDTO;
import com.backend.estoquerelogios.dto.LoginResponse;
import com.backend.estoquerelogios.dto.UserDTO;
import com.backend.estoquerelogios.service.TokenService;
import com.backend.estoquerelogios.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/user")
public class PublicController {

    private final UserService userService;
    private final TokenService tokenService;

    public PublicController(UserService userService, TokenService token) {
        this.userService = userService;
        this.tokenService = token;
    }

    @PostMapping("/create")
    @Transactional
    public ResponseEntity<?> createUserPost( @RequestBody CreateUserDTO infoUser){
        userService.createUser(infoUser);
        return ResponseEntity.ok().build();


    }

    @PostMapping("/login")
    @Transactional
    public ResponseEntity login(@RequestBody UserDTO userAccess){
        try{
            return ResponseEntity.ok(new LoginResponse(tokenService.createToken(userAccess), 300L));
        }catch (ResponseStatusException e){
            return ResponseEntity.badRequest().body("username or password is invalid");
        }

    }
}
