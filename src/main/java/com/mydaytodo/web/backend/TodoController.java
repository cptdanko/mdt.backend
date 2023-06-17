package com.mydaytodo.web.backend;

import com.mydaytodo.web.backend.models.Todo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/todo")
public class TodoController implements DefaultCrudController<Todo> {

    @Override
    @PostMapping("/add")
    public ResponseEntity add(@RequestBody Todo obj) {
        return new ResponseEntity(HttpStatus.OK);
    }
    @PutMapping("/:id")
    @Override
    public ResponseEntity<HttpStatus> update(@PathVariable("id") String id, @RequestBody Todo todo) {
        return null;
    }

    @DeleteMapping("/:id")
    @Override
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") String id) {
        return null;
    }
    @GetMapping("/:id")
    @Override
    public ResponseEntity<Todo> get(@PathVariable("id") String id) {
        return null;
    }
    @GetMapping("/by/:userId")
    public ResponseEntity<List<Todo>> getTodoForUser(@PathVariable("userId") String userId) {
        return null;
    }
}
