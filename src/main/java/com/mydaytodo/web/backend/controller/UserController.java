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
        // validation logic here
        User user = userService.addUser(obj);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> update(@PathVariable("id") String id, @RequestBody User obj) {
        // validation logic here
        Integer updateResponse = userService.update(id, obj);
        return new ResponseEntity<>(HttpStatus.valueOf(updateResponse));
    }
    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") String id) {
        //put some validation logic here
        Integer deleteResponse = userService.delete(id);
        return new ResponseEntity<>(HttpStatus.valueOf(deleteResponse));
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
