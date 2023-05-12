package com.khlopovskaya.ingoodhands.service;

import com.khlopovskaya.ingoodhands.entity.db.Shelter;
import com.khlopovskaya.ingoodhands.entity.db.User;
import com.khlopovskaya.ingoodhands.repository.UserRepo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    private final SessionFactory sessionFactory;
    private final UserRepo userRepo;
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public UserService(SessionFactory sessionFactory, UserRepo userRepo) {
        this.sessionFactory = sessionFactory;
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return userRepo.findByLogin(login);
    }

    public void create(User userDto) {
        User user = new User();
        user.setLogin(userDto.getLogin());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setRole(userDto.getRole());
        user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        userRepo.save(user);
    }

    public void saveShelter(User user, int shelterId) {
        user.setShelter(new Shelter(shelterId));
        userRepo.save(user);
    }



    public User getById(int id) {
        return userRepo.findById(id);
    }

    public List<User> getAll() {
        return userRepo.findAll();
    }

    public void save(User user) {
        userRepo.save(user);
    }

    public void delete(int id) {
        userRepo.deleteById(id);
    }
}