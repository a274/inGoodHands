package com.khlopovskaya.ingoodhands.repository;

import com.khlopovskaya.ingoodhands.entity.db.UserDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepo extends JpaRepository<UserDB, Integer> {
    UserDB findByLogin(String login);
    Long deleteById(int id);
    UserDB findById(int id);
}