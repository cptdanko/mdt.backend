package com.mydaytodo.web.backend.dao;

import com.mydaytodo.web.backend.models.User;

public interface UserDAO {
    User get(String userId);
    User save(User user);
    Integer delete(String userId);
    Integer update(String id, User user);
}
