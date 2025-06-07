package com.backend.estoquerelogios.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("log")
public class LogController {

    @GetMapping
    public ResponseEntity log(){
        return ResponseEntity.ok().build();
    }

}
