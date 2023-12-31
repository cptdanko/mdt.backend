package com.mydaytodo.web.backend.service;

import com.mydaytodo.web.backend.Constants;
import com.mydaytodo.web.backend.dao.UserDAO;
import com.mydaytodo.web.backend.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;

@Service
public class UserServiceImpl {
    @Autowired
    public UserDAO userDAO;

    public User getUser(String userId) {
        return userDAO.get(userId);
    }
    public Integer delete(String userId) {
        return userDAO.delete(userId);
    }
    public User addUser(User user) {
        String userId = Constants.USER_ID + System.currentTimeMillis();
        user.setUserId(userId);
        user.setDateJoined(new Date());
        user.setActive(true);
        return userDAO.save(user);
    }
    public Integer update(String id, User user) {
        return userDAO.update(id, user);
    }

    /**
     * @param user
     * @return
     */
    public User registerUser(User user) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        if(Arrays.stream(user.getRoles()).findAny().isEmpty()) {
            user.setRoles(new String[] {Constants.ROLES.ADMIN.toString()});
        }
        return userDAO.save(user);
    }
}
