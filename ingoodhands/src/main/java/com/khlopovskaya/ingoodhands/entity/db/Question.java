package com.khlopovskaya.ingoodhands.entity.db;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@Table(name = "test")
public class Question {

    @Id
    @Column(name = "question_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Column(name = "suggested_answers")
    private String suggestedAnswers;

    @NotNull
    @Column(name = "question_title")
    private String questionTitle;


}
