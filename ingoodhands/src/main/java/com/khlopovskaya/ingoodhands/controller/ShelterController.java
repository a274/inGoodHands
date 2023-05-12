package com.khlopovskaya.ingoodhands.controller;

import com.khlopovskaya.ingoodhands.entity.db.Shelter;
import com.khlopovskaya.ingoodhands.entity.db.User;
import com.khlopovskaya.ingoodhands.service.ShelterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shelter")
public class ShelterController {

    private final ShelterService shelterService;

    @Autowired
    public ShelterController(ShelterService shelterService) {
        this.shelterService = shelterService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable Integer id) {
        Shelter shelter = shelterService.getById(id);
        if (shelter == null)
            return ResponseEntity.badRequest().body("Shelter not found with id: " + id);
        return ResponseEntity.ok().body(shelter);
    }

    @PostMapping
    @PreAuthorize ("hasAuthority('OWNER')")
    public ResponseEntity<Object> create(Authentication authentication, @RequestBody Shelter shelter) {
        // TO DO try catch -> cast
        shelterService.create(shelter, (User) authentication.getPrincipal());
        return ResponseEntity.ok().body(shelter);
    }

    @GetMapping
    public ResponseEntity<Object> getAll() {
        List<Shelter> shelters = shelterService.getAll();
        return ResponseEntity.ok().body(shelters);
    }
}
