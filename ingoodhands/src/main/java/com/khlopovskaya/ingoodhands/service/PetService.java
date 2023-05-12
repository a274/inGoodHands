package com.khlopovskaya.ingoodhands.service;

import com.khlopovskaya.ingoodhands.entity.db.Pet;
import com.khlopovskaya.ingoodhands.repository.PetRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetService {
    private final PetRepo petRepo;

    @Autowired
    public PetService(PetRepo petRepo) {
        this.petRepo = petRepo;
    }

    public void create(Pet pet) {
        petRepo.save(pet);
    }

    public Pet getById(Integer id) {
        Optional<Pet> pet = petRepo.findById(id);
        return pet.orElse(null);
    }

    public List<Pet> getAll() {
        return petRepo.findAll();
    }
}
