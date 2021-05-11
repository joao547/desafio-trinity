package com.desafio.trinity.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/me")
@CrossOrigin("*")
public class MeController {

    @GetMapping
    public ResponseEntity test(){
        return ResponseEntity.ok("Acesso Restrito");
    }
}
