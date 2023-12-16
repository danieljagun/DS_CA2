package com.example.dsca2.service;

import com.example.dsca2.repository.User;

public interface UserService {

    User createUser(User user);

    User getUser(Long userID);

    User updateUser(Long userID, User updatedUser);

    void deleteUser(Long userID);
}
