package com.mydaytodo.web.backend;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloMDTController {
    @GetMapping("/api/greet")
    public String sayHello() {
        return "HelloWorld";
    }

    @GetMapping("/ping")
    public ResponseEntity<Void> ping() {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
