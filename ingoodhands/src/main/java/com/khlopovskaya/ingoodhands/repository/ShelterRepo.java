package com.khlopovskaya.ingoodhands.repository;

import com.khlopovskaya.ingoodhands.entity.db.Shelter;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShelterRepo extends JpaRepository<Shelter, Integer> {
    Optional<Shelter> findById(@NotNull Integer id);
}