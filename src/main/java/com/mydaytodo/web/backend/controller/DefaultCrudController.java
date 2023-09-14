package com.mydaytodo.web.backend.controller;

import org.springframework.http.ResponseEntity;

public interface DefaultCrudController<T> {

    ResponseEntity add(T obj);
    ResponseEntity update(String id, T obj);
    ResponseEntity delete(String id);
    ResponseEntity<T> get(String id);
}
