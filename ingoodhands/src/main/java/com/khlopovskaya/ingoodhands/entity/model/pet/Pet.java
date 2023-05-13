package com.khlopovskaya.ingoodhands.entity.model.pet;

import com.khlopovskaya.ingoodhands.entity.db.PetDB;
import com.khlopovskaya.ingoodhands.entity.db.Shelter;
import com.khlopovskaya.ingoodhands.entity.model.user.User;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.Period;

@NoArgsConstructor
@AllArgsConstructor
public class Pet {

    private int id;

    @NotNull
    private String name;

    @NotNull
    private PetStatus status;

    private String characteristic;

    @NotNull
    private Shelter shelter;

    private LocalDate birthdate;

    @NotNull
    private PetColour colour;

    @NotNull
    private PetSpecies species;

    @NotNull
    private PetSize size;

    @NotNull
    private PetCharacter character;

    @NotNull
    private PetGender gender;

    @NotNull
    private User employee;

    public PetAge getPetAge() {
        int age = getAge();
        if (age <= 1) return PetAge.TO1YEAR;
        if (age >= 10) return PetAge.OVER10YEARS;
        return PetAge.MIDDLE_AGED;
    }

    public int getAge() {
        LocalDate now = LocalDate.now();
        return Period.between(now, birthdate).getYears();
    }

    public Pet(PetDB petDB) {
        if (petDB != null) {
            this.id = petDB.getId();
            this.name = petDB.getName();
            this.status = petDB.getStatus();
            this.characteristic = petDB.getCharacteristic();
            this.shelter = petDB.getShelter();
            this.birthdate = petDB.getBirthdate();
            this.colour = petDB.getColour();
            this.species = petDB.getSpecies();
            this.size = petDB.getSize();
            this.character = petDB.getCharacter();
            this.gender = petDB.getGender();
            this.employee = petDB.getEmployee().toUser();
        }
    }
}
