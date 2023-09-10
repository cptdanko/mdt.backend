package com.mydaytodo.web.backend.dao;

import com.mydaytodo.web.backend.config.DynamoDBConfig;
import com.mydaytodo.web.backend.models.Todo;
import com.mydaytodo.web.backend.models.User;
import com.mydaytodo.web.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
@Component
public class UserDAOImpl implements UserDAO {
    private Logger logger = Logger.getLogger(UserDAOImpl.class.toString());
    @Autowired
    private DynamoDBAdmin dynamoDBAdmin;

    @Autowired
    private UserRepository userRepository;

    @Override
    public User get(String userId) {
        return userRepository.getUser(userId);
    }

    @Override
    public User save(User user) {
        return userRepository.saveUser(user);
    }

    /**
     * Delete user only marks user active status
     * as false
     * @param userId
     * @return
     */
    @Override
    public Integer delete(String userId) {
        return userRepository.deleteUser(userId);
    }

    @Override
    public Integer update(String id, User user) {
        return userRepository.updateUser(id, user);
    }
}