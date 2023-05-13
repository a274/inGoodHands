package com.khlopovskaya.ingoodhands.controller;

import com.khlopovskaya.ingoodhands.entity.db.PetDB;
import com.khlopovskaya.ingoodhands.entity.model.pet.*;
import com.khlopovskaya.ingoodhands.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pet")
public class PetController {

    final private PetService petService;

    @Autowired
    public PetController(PetService petService) {
        this.petService = petService;
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('OWNER','EMPLOYEE')")
    public ResponseEntity<Object> create(Authentication authentication, @RequestBody PetDB petDB) {
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
    public List<Pet> getAllFiltered(@RequestParam(required = false) PetAge age,
                                    @RequestParam(required = false) PetCharacter character,
                                    @RequestParam(required = false) PetColour colour,
                                    @RequestParam(required = false) PetSpecies species,
                                    @RequestParam(required = false) PetSize size,
                                    @RequestParam(required = false) PetGender gender) {
        return petService.getAllFiltered(age, character, colour, species, size, gender);
    }
}
