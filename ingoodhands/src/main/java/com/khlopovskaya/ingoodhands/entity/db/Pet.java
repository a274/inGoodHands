package com.khlopovskaya.ingoodhands.entity.db;

import com.khlopovskaya.ingoodhands.entity.model.pet.*;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.Period;

@Getter
@Setter
@Entity
@Table(name = "pet")
public class Pet {

    @Id
    @Column(name = "pet_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Column(name = "pet_name")
    private String name;

    @NotNull
    @Column(name = "pet_status")
    private PetStatus status;

    @Column(name = "pet_characteristic")
    private String characteristic;

    @NotNull
    @OneToOne
    @JoinColumn(name = "shelter_shelter_id")
    private Shelter shelter;

    @Column(name = "pet_birthdate")
    private LocalDate birthdate;

    @NotNull
    @Column(name = "pet_colour")
    private PetColour colour;

    @NotNull
    @Column(name = "pet_species")
    private PetSpecies species;

    @NotNull
    @Column(name = "pet_size")
    private PetSize size;

    @NotNull
    @Column(name = "pet_character")
    private PetCharacter character;

    @NotNull
    @Column(name = "pet_gender")
    private PetGender gender;

    @NotNull
    @OneToOne
    @JoinColumn(name = "employee_user_id")
    private User employee;

    public int getAge() {
        LocalDate now = LocalDate.now();
        return Period.between(now, birthdate).getYears();
    }

}
