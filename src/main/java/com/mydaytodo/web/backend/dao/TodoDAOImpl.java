package com.mydaytodo.web.backend.dao;

import com.mydaytodo.web.backend.config.DynamoDBConfig;
import com.mydaytodo.web.backend.models.Todo;
import com.mydaytodo.web.backend.repository.TodoRepository;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

@Component
public class TodoDAOImpl implements TodoDAO {
    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private DynamoDBConfig dynamoDBConfig;
    @Override
    public List<Todo> getTodoByUser(String userId) {
        return todoRepository.getTodoByUser(userId);
        // return todos.stream().filter(todo -> todo.getUserId().equals(userId)).toList();
    }

    @Override
    public List<Todo> searchTodo(String userId, String searchTerm) {
        return getTodoByUser(userId)
                .stream()
                .filter(todo -> todo.getText().toLowerCase().contains(searchTerm.toLowerCase()))
                .toList();
    }

    @Override
    public Integer deleteTodo(String todoId) {
        return todoRepository.deleteTodo(todoId);
    }

    @Override
    public boolean updateTodo(String todoId, Todo todo) {
        return todoRepository.updateTodo(todoId, todo) == HttpStatus.SC_NO_CONTENT;
    }

    @Override
    public Todo getTodo(String todoId) {
        return todoRepository.getTodo(todoId);
    }

    @Override
    public Todo addTodo(Todo todo) {
        return todoRepository.saveTodo(todo);
    }

}
