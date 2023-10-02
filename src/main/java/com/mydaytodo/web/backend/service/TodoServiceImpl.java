package com.mydaytodo.web.backend.service;

import com.mydaytodo.web.backend.Constants;
import com.mydaytodo.web.backend.dao.TodoDAO;
import com.mydaytodo.web.backend.models.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TodoServiceImpl {
    @Autowired
    public TodoDAO todoDAO;

    public List<Todo> getTodoByUser(String userId) {
        return todoDAO.getTodoByUser(userId);
    }


    public Integer deleteTodo(String todoId) {
        return todoDAO.deleteTodo(todoId);
    }

    public boolean updateTodo(String todoId, Todo todo) {
        return todoDAO.updateTodo(todoId, todo);
    }

    public Todo addTodo(Todo todo) {
        String todoId = Constants.TODO_ID + System.currentTimeMillis();
        todo.setId(todoId);
        return todoDAO.addTodo(todo);
    }

    public Todo getTodo(String todoId) {
        return todoDAO.getTodo(todoId);
    }

    public List<Todo> searchTodo(String userId, String searchTerm) {
        return todoDAO.searchTodo(userId, searchTerm);
    }
}
