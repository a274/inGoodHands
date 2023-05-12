package com.khlopovskaya.ingoodhands.service;

import com.khlopovskaya.ingoodhands.entity.db.Shelter;
import com.khlopovskaya.ingoodhands.entity.db.User;
import com.khlopovskaya.ingoodhands.repository.ShelterRepo;
import com.khlopovskaya.ingoodhands.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ShelterService {

    private final ShelterRepo shelterRepo;
    private final UserService userService;

    @Autowired
    public ShelterService(ShelterRepo shelterRepo, UserService userService) {
        this.shelterRepo = shelterRepo;
        this.userService = userService;
    }

    public void create(Shelter shelter, User user) {
        shelterRepo.save(shelter);
        userService.saveShelter(user, shelter.getId());
    }

    public Shelter getById(Integer id) {
        Optional<Shelter> shelter = shelterRepo.findById(id);
        return shelter.orElse(null);
    }

    public List<Shelter> getAll() {
        return shelterRepo.findAll();
    }

}
