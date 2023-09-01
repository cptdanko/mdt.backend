package com.mydaytodo.web.backend.dao;

import com.mydaytodo.web.backend.models.Todo;

import java.util.List;

public interface TodoDAO {
    List<Todo> getTodoByUser(String userId);
    Integer deleteTodo(String todoId);
    boolean updateTodo(String todoId, Todo todo);
    Todo getTodo(String todoId);
    Todo addTodo(Todo todo);
}
