package com.khlopovskaya.ingoodhands.repository;

import com.khlopovskaya.ingoodhands.entity.db.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    User findByLogin(String login);
    Long deleteById(int id);
    User findById(int id);
}