package com.mydaytodo.web.backend.dao;

import com.mydaytodo.web.backend.models.Todo;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
@Component
public class TodoDAOImpl implements TodoDAO {

    private List<Todo> todos = new ArrayList<>();
    @Override
    public List<Todo> getTodoByUser(String userId) {
        List<Todo> userTodos = todos.stream().filter(todo -> {
            return todo.getUserId().equals(userId);
        }).collect(Collectors.toList());
        return userTodos;
    }

    @Override
    public boolean deleteTodo(String todoId) {
        return todos.removeIf(todo -> (todo.getId().equals(todoId)));
    }

    @Override
    public boolean updateTodo(String todoId, Todo todo) {
        AtomicInteger count = new AtomicInteger(0);
        List<Todo> filteredList = todos.stream().filter(t -> {
            count.addAndGet(1);
            return t.getId().equals(todoId);
        }).collect(Collectors.toList());
        if(count == null) {
            return false;
        }
        todos.set((count.get() - 1), todo);
        return true;
    }

    @Override
    public Todo getTodo(String todoId) {
        List<Todo> filteredList = todos.stream().filter(todo -> {
            return todo.getId().equals(todoId);
        }).collect(Collectors.toList());
        //filteredList.size() > 0
        return filteredList.stream().findFirst().orElseThrow();
    }

    @Override
    public Todo addTodo(Todo todo) {
        if(todos.add(todo)) {
            return todo;
        }
        return null;
    }
}
