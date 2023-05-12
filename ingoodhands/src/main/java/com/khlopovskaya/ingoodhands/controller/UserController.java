package com.khlopovskaya.ingoodhands.controller;

import com.khlopovskaya.ingoodhands.entity.AuthData;
import com.khlopovskaya.ingoodhands.entity.db.User;
import com.khlopovskaya.ingoodhands.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    @PostAuthorize("returnObject.login == authentication.principal.login or hasRole('ADMIN')")
    public User getById(@PathVariable Integer id) {
        return userService.getById(id);
    }

    @GetMapping("/sign")
    public ResponseEntity<Object> signIn(HttpServletRequest request, @RequestBody AuthData authData) {
        authWithHttpServletRequest(request, authData.getUsername(), authData.getPassword());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/sign")
    public ResponseEntity<Object> signUp(HttpServletRequest request, @RequestBody User user) {
        if (userService.loadUserByUsername(user.getLogin()) == null) {
            userService.create(user);
        }
        authWithHttpServletRequest(request, user.getLogin(), user.getPassword());
        return ResponseEntity.ok().build();
    }

    public void authWithHttpServletRequest(HttpServletRequest request, String username, String password) {
        try {
            request.login(username, password);
        } catch (ServletException ignored) { }
    }
}