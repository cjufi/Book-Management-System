package com.cjufi.bookmanagement.service;

import com.cjufi.bookmanagement.model.User;

import java.util.Optional;

public interface UserService {
    User saveUser(User user);

    Optional<User> findByUsername(String username);

    void makeAdmin(String username);

    String login(String username, String password);

    String signUp(User user);
}
