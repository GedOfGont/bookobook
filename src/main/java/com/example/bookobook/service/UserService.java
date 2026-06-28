package com.example.bookobook.service;

import java.util.Optional;
import org.springframework.stereotype.Service;
import com.example.bookobook.model.User;
import com.example.bookobook.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void save(User user) {
        userRepository.save(user);
    }
}
