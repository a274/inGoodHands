package com.khlopovskaya.ingoodhands.repository;

import com.khlopovskaya.ingoodhands.entity.db.PetDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PetRepo extends JpaRepository<PetDB, Integer> {
    Optional<PetDB> findById(Integer id);
}