package com.mydaytodo.web.backend.controller;

import com.mydaytodo.web.backend.config.KeyConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
public class HelloMDTController {
    @Autowired
    private KeyConfig keyConfig;

    @GetMapping("/api/greet")
    public String sayHello() {
        return "HelloWorld";
    }

    @GetMapping("/ping")
    public ResponseEntity<Void> ping() {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
