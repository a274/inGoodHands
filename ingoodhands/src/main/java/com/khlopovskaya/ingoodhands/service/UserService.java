package com.khlopovskaya.ingoodhands.service;

import com.khlopovskaya.ingoodhands.entity.db.Shelter;
import com.khlopovskaya.ingoodhands.entity.db.UserDB;
import com.khlopovskaya.ingoodhands.entity.model.user.ShelterEmployee;
import com.khlopovskaya.ingoodhands.entity.model.user.User;
import com.khlopovskaya.ingoodhands.factory.UserFactory;
import com.khlopovskaya.ingoodhands.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    private final UserRepo userRepo;
    private final UserFactory userFactory;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepo userRepo, UserFactory userFactory, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.userFactory = userFactory;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        UserDB userDB = getByLogin(login);
        return userFactory.createUser(userDB);
    }

    public void create(@NotNull User user) {
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        UserDB userDB = user.toUserDB();
        userRepo.save(userDB);
    }

    public void saveShelter(@NotNull User user, int shelterId) {
        UserDB userDB = user.toUserDB();
        userDB.setShelter(new Shelter(shelterId));
        userRepo.save(userDB);
    }

    private UserDB getByLogin(String login) {
        return userRepo.findByLogin(login);
    }

    public User getById(int id) {
        UserDB userDB = getByIdFromDB(id);
        return userFactory.createUser(userDB);
    }

    private UserDB getByIdFromDB(int id) {
        return userRepo.findById(id);
    }

    public List<UserDB> getAll() {
        return userRepo.findAll();
    }

    public void save(UserDB userDB) {
        userRepo.save(userDB);
    }

    public void delete(int id) {
        userRepo.deleteById(id);
    }

    public void updateShelter(ShelterEmployee employee, int shelterId) {
        UserDB userDB = employee.toUserDB();
        userDB.setShelter(new Shelter(shelterId));
        userRepo.save(userDB);
    }

}