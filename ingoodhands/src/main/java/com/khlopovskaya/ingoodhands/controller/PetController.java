package com.khlopovskaya.ingoodhands.controller;

import com.khlopovskaya.ingoodhands.entity.db.PetDB;
import com.khlopovskaya.ingoodhands.entity.model.pet.*;
import com.khlopovskaya.ingoodhands.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Object> create(@RequestBody PetDB petDB) {
        petService.create(petDB);
        return ResponseEntity.ok().body(petDB);
    }
/*
    @GetMapping
    public ResponseEntity<Object> getAll() {
        List<Pet> pets = petService.getAll();
        return ResponseEntity.ok().body(pets);
    }*/


    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable Integer id) {
        Pet pet = petService.getById(id);
        if (pet == null)
            return ResponseEntity.badRequest().body("Pet not found with id: " + id);
        return ResponseEntity.ok().body(pet);
    }

    @GetMapping
    public ResponseEntity<Object> getAllFiltered(@RequestParam PetAge age,
                                                 @RequestParam PetCharacter character,
                                                 @RequestParam PetColour colour,
                                                 @RequestParam PetSpecies species,
                                                 @RequestParam PetSize size,
                                                 @RequestParam PetGender gender) {
        List<Pet> pets = petService.getAllFiltered(age, character, colour, species, size, gender);
        return ResponseEntity.ok().body(pets);
    }
}
