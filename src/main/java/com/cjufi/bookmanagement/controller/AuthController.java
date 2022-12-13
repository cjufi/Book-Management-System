package com.cjufi.bookmanagement.controller;

import com.cjufi.bookmanagement.model.LoginRequest;
import com.cjufi.bookmanagement.model.User;
import com.cjufi.bookmanagement.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class AuthController {

    private UserService userService;

    @PostMapping("/login")
    public String token(@RequestBody LoginRequest loginRequest) {
        return userService.login(loginRequest.getUsername(), loginRequest.getUsername());
    }

    @PostMapping("/signup")
    public String sign(@RequestBody User user) {
        return userService.signUp(user);
    }

    @PostMapping("/make-admin")
    public ResponseEntity<?> admin(@RequestBody String username) {
        userService.makeAdmin(username);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}