package com.vnpt.shopguard.service;

import com.vnpt.shopguard.model.User;
import com.vnpt.shopguard.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User login(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    public User register(User user) {
        if (userRepository.findByUsername(user.getUsername()) == null) {
            return userRepository.save(user);
        }
        return null;
    }
}
