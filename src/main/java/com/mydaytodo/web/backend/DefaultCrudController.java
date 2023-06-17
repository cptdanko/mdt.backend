package com.mydaytodo.web.backend;

import org.springframework.http.ResponseEntity;

public interface DefaultCrudController<T> {

    public ResponseEntity add(T obj);
    public ResponseEntity update(String id, T obj);
    public ResponseEntity delete(String id);
    public ResponseEntity<T> get(String id);
}
