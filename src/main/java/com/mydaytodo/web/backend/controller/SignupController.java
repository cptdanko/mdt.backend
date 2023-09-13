package com.mydaytodo.web.backend.controller;

import com.mydaytodo.web.backend.models.User;
import org.apache.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class SignupController {

    @GetMapping("/register")
    public ResponseEntity<HttpStatus> signup(@RequestBody User user) {
        return new ResponseEntity<>(HttpStatusCode.valueOf(201));
    }

    /**
     * Ensure this method caters for logging in using
     * username/password to return a token
     * logging in for oauth2 using Google
     * logging in using username/password for basic auth
     * @param user
     * @return
     */
    @GetMapping("/login")
    public ResponseEntity<HttpStatus> login(@RequestBody User user) {
        //authenticate
        return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }
}
