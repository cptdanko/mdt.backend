package com.mydaytodo.web.backend.controller;

import com.mydaytodo.web.backend.models.Todo;
import com.mydaytodo.web.backend.service.TodoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/todo")
@RestController
public class TodoController implements DefaultCrudController<Todo> {

    @Autowired
    private TodoServiceImpl todoService;
    /**
     * add validation to return
     * 201 created
     * 400 bad request, in case it's called without todo
     * 500 internal server error
     * @param obj
     * @return
     */
    @Override
    @PostMapping("/add")
    public ResponseEntity<HttpStatus> add(@RequestBody Todo obj) {
        ResponseEntity result;
        // validation logic here
        Todo todo = todoService.addTodo(obj);
        if (todo != null) {
            result = new ResponseEntity<>(todo, HttpStatus.CREATED);
        } else {
            result = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return result;
    }
    /**
     * add validation to return
     * 204 update done
     * 404 todo with id not found
     * 422 unproccessable content, something wrong with update body
     * 500 server error
     * @param id
     * @param todo
     * @return
     */
    @PutMapping("/{id}")
    @Override
    public ResponseEntity<HttpStatus> update(@PathVariable("id") String id, @RequestBody Todo todo) {
        ResponseEntity<HttpStatus> result;
        // validation logic here
        if (todoService.updateTodo(id, todo)) {
            result = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            result = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return result;
    }

    /**
     * add validation to return
     * 204 delete success
     * 404 not found
     * 409 Conflict in the state of the resource
     * 403 forbidden if a user tries deleting another user todo
     * 500 in case of a server error
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") String id) {
        //put some validation logic here
        int delResult = todoService.deleteTodo(id);
        return new ResponseEntity<>(HttpStatus.valueOf(delResult));
    }

    /**
     * add validation to return
     * 200 success
     * 400 bad request
     * 403 forbidden
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @Override
    public ResponseEntity<Todo> get(@PathVariable("id") String id) {
        ResponseEntity<Todo> result;
        // validation logic here
        Todo todo = todoService.getTodo(id);
        if (todo != null) {
            result = new ResponseEntity<>(todo, HttpStatus.OK);
        } else {
            result = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return result;
    }
    /**
     * add validation to return
     * 200 success
     * 403 forbidden
     * 500 internal server error
     * @param userId
     * @return
     */
    @GetMapping("/by/{userId}")
    public ResponseEntity<List<Todo>> getTodoForUser(@PathVariable("userId") String userId) {
        ResponseEntity<List<Todo>> result;
        // validation logic here
        List<Todo> todos = this.todoService.getTodoByUser(userId);
        if (todos != null) {
            result = new ResponseEntity<>(todos, HttpStatus.OK);
        } else {
            result = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return result;
    }
}
