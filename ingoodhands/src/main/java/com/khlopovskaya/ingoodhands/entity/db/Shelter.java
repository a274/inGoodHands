package com.khlopovskaya.ingoodhands.entity.db;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@Table(name = "shelter")
@NoArgsConstructor
public class Shelter {

    @Id
    @Column(name = "shelter_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Column(name = "shelter_name")
    private String name;

    @Column(name = "shelter_url")
    private String url;

    public Shelter(int id) {
        this.id = id;
    }
}
