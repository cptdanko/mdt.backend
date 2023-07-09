package com.mydaytodo.web.backend.controller;

import com.mydaytodo.web.backend.models.User;
import com.mydaytodo.web.backend.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController implements DefaultCrudController<User> {
    @Autowired
    private UserServiceImpl userService;
    @Override
    @PostMapping("/")
    public ResponseEntity<User> add(@RequestBody User obj) {
        ResponseEntity result;
        // validation logic here
        User user = userService.addUser(obj);
        if (user != null) {
            result = new ResponseEntity(user, HttpStatus.OK);
        } else {
            result = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return result;
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable("id") String id, @RequestBody User obj) {
        ResponseEntity<User> result;
        // validation logic here
        User user = userService.update(id, obj);
        if (user != null) {
            result = new ResponseEntity<>(user, HttpStatus.NO_CONTENT);
        } else {
            result = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return result;
    }
    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity delete(@PathVariable("id") String id) {
        ResponseEntity<HttpStatus> result;
        //put some validation logic here
        if (userService.delete(id)) {
            result = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            result = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return result;
    }
    @GetMapping("/{id}")
    @Override
    public ResponseEntity<User> get(@PathVariable("id") String id) {
        ResponseEntity<User> result;
        // validation logic here
        User user = userService.getUser(id);
        if (user != null) {
            result = new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            result = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return result;
    }
}
