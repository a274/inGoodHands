package com.khlopovskaya.ingoodhands.entity.db;

import com.khlopovskaya.ingoodhands.entity.model.user.User;
import com.khlopovskaya.ingoodhands.entity.model.user.UserRole;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "user_")
public class UserDB {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Column(name = "user_firstname")
    private String firstName;

    @NotNull
    @Column(name = "user_lastname")
    private String lastName;

    @NotNull
    @Column(name = "user_role")
    private UserRole role;

    @NotNull
    @Column(name = "user_login")
    private String login;

    @NotNull
    @Column(name = "user_pswd")
    private String password;

    @OneToOne
    @JoinColumn(name = "result_result_id")
    private TestResult result;

    @OneToMany
    private List<PetDB> favouritePetDBS;

    @OneToOne
    @JoinColumn(name = "shelter_shelter_id")
    private Shelter shelter;

    public User toUser() {
        User user = new User();
        user.setId(this.getId());
        user.setLogin(this.getLogin());
        user.setFirstName(this.getFirstName());
        user.setLastName(this.getLastName());
        user.setRole(this.getRole());
        user.setPassword(this.getPassword());
        return user;
    }
}