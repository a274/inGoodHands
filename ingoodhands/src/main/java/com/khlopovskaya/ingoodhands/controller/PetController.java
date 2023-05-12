package com.khlopovskaya.ingoodhands.controller;

import com.khlopovskaya.ingoodhands.entity.db.Pet;
import com.khlopovskaya.ingoodhands.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/pet")
public class PetController {

    final private PetService petService;

    @Autowired
    public PetController(PetService petService) {
        this.petService = petService;
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody Pet pet) {
        petService.create(pet);
        return ResponseEntity.ok().body(pet);
    }

    @GetMapping
    public ResponseEntity<Object> getAll() {
        List<Pet> pets = petService.getAll();
        return ResponseEntity.ok().body(pets);
    }
}
