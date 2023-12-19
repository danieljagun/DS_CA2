package com.example.dsca2.service;

import com.example.dsca2.repository.User;
import com.example.dsca2.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {
        User newUser = new User(user.getUserID(), user.getName(), user.getPassword());
        User savedUser = userRepository.save(newUser);
        logger.info("Created new user with ID: {}", savedUser.getUserID());
        return savedUser;
    }

    @Override
    public User getUserByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public User getUser(Long userID) {
        Optional<User> userOptional = userRepository.findById(userID);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            logger.info("Retrieved user with ID: {}", user.getUserID());
            return user;
        } else {
            logger.info("User with ID {} not found", userID);
            return null;
        }
    }

    @Override
    public User updateUser(Long userID, User updatedUser) {
        Optional<User> userOptional = userRepository.findById(userID);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setName(updatedUser.getName());
            user.setPassword(updatedUser.getPassword());

            User savedUser = userRepository.save(user);
            if (savedUser != null) {
                logger.info("Updated user with ID: {}", savedUser.getUserID());
                return savedUser;
            } else {
                logger.error("Failed to update user with ID: {}", userID);
                return null;
            }
        } else {
            logger.info("User with ID {} not found", userID);
            return null;
        }
    }

    @Override
    public void deleteUser(Long userID) {
        Optional<User> userOptional = userRepository.findById(userID);
        if (userOptional.isPresent()) {
            userRepository.deleteById(userID);
            logger.info("Deleted user with ID: {} ", userID);
        } else {
            logger.info("User with ID {} not found, deletion failed.", userID);
        }
    }
}
