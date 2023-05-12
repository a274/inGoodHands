package com.khlopovskaya.ingoodhands.repository;

import com.khlopovskaya.ingoodhands.entity.db.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PetRepo extends JpaRepository<Pet, Integer> {
    Optional<Pet> findById(Integer id);
}