package com.khlopovskaya.ingoodhands.entity.model;

import com.khlopovskaya.ingoodhands.entity.db.Pet;
import com.khlopovskaya.ingoodhands.entity.db.Shelter;
import com.khlopovskaya.ingoodhands.entity.model.pet.*;

import java.util.List;

public class Sponsor extends AbstractUser {

    @Override
    public List<Pet> getAllPets() {
        return null;
    }

    @Override
    public List<Pet> getAllPets(int age, PetCharacter character, PetColour colour, PetSpecies species, PetSize size, PetGender gender) {
        return null;
    }

    @Override
    public List<Shelter> getAllShelters() {
        return null;
    }

    @Override
    public void donate(Shelter shelter, double sum) {

    }
}
