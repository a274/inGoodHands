package com.khlopovskaya.ingoodhands.controller;

import com.khlopovskaya.ingoodhands.entity.model.user.ShelterEmployee;
import com.khlopovskaya.ingoodhands.entity.model.user.User;
import com.khlopovskaya.ingoodhands.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
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
    public ResponseEntity<Object> signIn(HttpServletRequest request, @RequestBody User user) {
        authWithHttpServletRequest(request, user.getLogin(), user.getPassword());
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

    @PutMapping("/shelter")
    @PreAuthorize("hasAuthority('EMPLOYEE')")
    public ResponseEntity<Object> updateShelter(Authentication authentication, @RequestParam int shelterId) {
        userService.updateShelter((ShelterEmployee) authentication.getPrincipal(), shelterId);
        return ResponseEntity.noContent().build();
    }
}