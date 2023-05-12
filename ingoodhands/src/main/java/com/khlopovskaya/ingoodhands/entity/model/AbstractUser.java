package com.khlopovskaya.ingoodhands.entity.model;

import com.khlopovskaya.ingoodhands.entity.db.Pet;
import com.khlopovskaya.ingoodhands.entity.db.Shelter;
import com.khlopovskaya.ingoodhands.entity.db.UserRole;
import com.khlopovskaya.ingoodhands.entity.model.pet.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public abstract class AbstractUser {
    protected String firstName;
    protected String lastName;
    protected String login;
    protected String password;
    private UserRole role;

    abstract public List<Pet> getAllPets();

    abstract public List<Pet> getAllPets(int age,
                                         PetCharacter character,
                                         PetColour colour,
                                         PetSpecies species,
                                         PetSize size,
                                         PetGender gender);

    abstract public List<Shelter> getAllShelters();

    abstract public void donate(Shelter shelter, double sum);

}
