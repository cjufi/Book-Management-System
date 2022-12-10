package com.cjufi.bookmanagement.service.impl;

import com.cjufi.bookmanagement.model.Role;
import com.cjufi.bookmanagement.model.User;
import com.cjufi.bookmanagement.repository.UserRepo;
import com.cjufi.bookmanagement.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private UserRepo userRepo;

    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public User saveUser(User user) {
        user.setPassword(passwordEncoder().encode(user.getPassword()));
        user.setRole(Role.USER);
        user.setCreateTime(LocalDateTime.now());
        return userRepo.save(user);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    @Transactional
    @Override
    public void makeAdmin(String username) {
        userRepo.updateUserRole(username,Role.ADMIN);
    }
}
