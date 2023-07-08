package com.mydaytodo.web.backend.dao;

import com.mydaytodo.web.backend.models.Todo;
import com.mydaytodo.web.backend.models.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
@Component
public class UserDAOImpl implements UserDAO {
    private Logger logger = Logger.getLogger(UserDAOImpl.class.toString());

    private List<User> users = new ArrayList<>();
    @Override
    public User get(String userId) {
        logger.info("IN USER DAO IMPL "+ userId);
        List<User> filteredList = users.stream().filter(user -> {
            return user.getUserId().equals(userId);
        }).collect(Collectors.toList());
        return filteredList.stream().findFirst().orElseThrow();
    }

    @Override
    public User save(User user) {
        if(users.add(user)) {
            return user;
        }
        return null;
    }

    /**
     * Delete user only marks user active status
     * as false
     * @param userId
     * @return
     */
    @Override
    public boolean delete(String userId) {
        User user = users.stream().filter(u -> u.getUserId().equals(userId))
                .findFirst().orElseThrow();
        user.setActive(false);
        return (update(userId, user) != null);
    }

    @Override
    public User update(String id, User user) {
        User savedUser = users.stream().filter(user1 -> (user1.getUserId().equals(id)))
                .findFirst().orElseThrow();
        savedUser.setName(user.getName());
        savedUser.setEmail(user.getEmail());
        savedUser.setPreferredCurrencyCode(user.getPreferredCurrencyCode());
        savedUser.setUsername(user.getUsername());
        users.set(users.indexOf(savedUser), savedUser);
        return user;
    }
}
