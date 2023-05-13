package com.khlopovskaya.ingoodhands.service;

import com.khlopovskaya.ingoodhands.entity.db.PetDB;
import com.khlopovskaya.ingoodhands.entity.model.pet.*;
import com.khlopovskaya.ingoodhands.repository.PetRepo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PetService {
    private final PetRepo petRepo;
    private final SessionFactory sessionFactory;

    @Autowired
    public PetService(PetRepo petRepo, SessionFactory sessionFactory) {
        this.petRepo = petRepo;
        this.sessionFactory = sessionFactory;
    }

    public void create(PetDB petDB) {
        petRepo.save(petDB);
    }

    private PetDB getByIdFromDB(Integer id) {
        Optional<PetDB> pet = petRepo.findById(id);
        return pet.orElse(null);
    }

    public Pet getById(Integer id) {
        PetDB petDB = getByIdFromDB(id);
        return new Pet(petDB);
    }

    public List<Pet> getAll() {
        List<PetDB> petDBS = getAllFromDB();
        return petDBS.stream().map(Pet::new).collect(Collectors.toList());
    }

    public List<Pet> getAllFiltered(PetAge age,
                                    PetCharacter character,
                                    PetColour colour,
                                    PetSpecies species,
                                    PetSize size,
                                    PetGender gender) {
        List<PetDB> petDBS = getAllFromDBFiltered(age, character, colour, species, size, gender);
        return petDBS.stream().map(Pet::new).collect(Collectors.toList());
    }

    private List<PetDB> getAllFromDBFiltered(PetAge age,
                                             PetCharacter character,
                                             PetColour colour,
                                             PetSpecies species,
                                             PetSize size,
                                             PetGender gender) {
        String queryString = getQuery(species, colour, gender,  character, size, age);
        Session session = sessionFactory.openSession();
        Query<PetDB> query = session.createQuery(queryString, PetDB.class);

        query.setParameter("status", PetStatus.READY);
        if (species != null) query.setParameter("species", species);
        if (colour != null) query.setParameter("colour", colour);
        if (gender != null) query.setParameter("gender", gender);
        if (size != null) query.setParameter("size", size);
        if (character != null) query.setParameter("character", character);
        List<PetDB> results = query.list();
        session.close();
        return results;
    }

    private String getQuery(PetSpecies species,
                            PetColour colour,
                            PetGender gender,
                            PetCharacter character,
                            PetSize size,
                            PetAge petAge) {
        StringBuilder query = new StringBuilder("FROM PetDB u WHERE u.status = :status");
        if (species != null) query.append(" and u.species  = :species");
        if (colour != null) query.append(" and u.colour = :colour");
        if (gender != null) query.append(" and u.gender = :gender");
        if (size != null) query.append(" and u.size = :size");
        if (character != null) query.append(" and u.character = :character");
        if (petAge != null) {
            switch (petAge) {
                case TO1YEAR -> query.append(" and u.birthdate >= (current_date - 365)");
                case MIDDLE_AGED -> query.append(" and u.birthdate <= (current_date - 365) and u.birthdate >= (current_date - 3650)");
                case OVER10YEARS -> query.append(" u.birthdate <= (current_date - 3650)");
            }
        }
        return query.toString();
    }


    private List<PetDB> getAllFromDB() {
        return petRepo.findAll();
    }
}
