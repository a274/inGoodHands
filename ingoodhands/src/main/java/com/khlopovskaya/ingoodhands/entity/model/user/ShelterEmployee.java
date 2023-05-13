package com.khlopovskaya.ingoodhands.entity.model.user;

import com.khlopovskaya.ingoodhands.entity.db.Shelter;
import com.khlopovskaya.ingoodhands.entity.db.UserDB;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
public class ShelterEmployee extends Sponsor {

    @Getter
    @Setter
    @NotNull
    private Shelter workShelter;

    public ShelterEmployee(int id, String firstName, String lastName, String login, String password, UserRole role) {
        super(id, firstName, lastName, login, password, role);
    }

    public ShelterEmployee(User user) {
        super(user.getId(), user.getFirstName(), user.getLastName(), user.getLogin(), user.getPassword(), user.getRole());
    }

    public ShelterEmployee(UserDB user) {
        super(user.getId(), user.getFirstName(), user.getLastName(), user.getLogin(), user.getPassword(), user.getRole());
        this.workShelter = user.getShelter();
    }
}
