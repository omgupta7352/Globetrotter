package com.SpringBootMVC.demo.service;

import com.SpringBootMVC.demo.entity.Users;
import com.SpringBootMVC.demo.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    // Register a new user
    public Users registerUser(String username) {
        Users user = new Users();
        user.setUsername(username);
        user.setScore(0);  // Set default score to 0
        return userRepository.save(user);
    }

    public Optional<Users> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void updateUserScore(Users user, int score) {
        user.setScore(user.getScore() + score);
        userRepository.save(user);
    }
}
