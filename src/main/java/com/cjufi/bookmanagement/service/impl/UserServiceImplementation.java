package com.cjufi.bookmanagement.service.impl;

import com.cjufi.bookmanagement.model.Role;
import com.cjufi.bookmanagement.model.User;
import com.cjufi.bookmanagement.repository.UserRepo;
import com.cjufi.bookmanagement.service.TokenService;
import com.cjufi.bookmanagement.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserServiceImplementation implements UserService {

    private UserRepo userRepo;
    private AuthenticationManager manager;
    private TokenService tokenService;
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

    @Override
    public String login(String username, String password) {
        if (userRepo.findByUsername(username) == null) throw new UsernameNotFoundException("User not found");
        Authentication authentication = manager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
        String token = tokenService.generateToken(authentication);
        return token;
    }

    @Override
    public String signUp(User user) {
        if(userRepo.findByUsername(user.getUsername()) != null) throw new UsernameNotFoundException("User with set username, already exists");
        user.setRole(Role.USER);
        user.setCreateTime(LocalDateTime.now());
        userRepo.save(user);
        return "You have successfully signed up";
    }
}
