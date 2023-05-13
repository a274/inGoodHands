package com.khlopovskaya.ingoodhands.entity.model.user;

import com.khlopovskaya.ingoodhands.entity.db.Shelter;
import com.khlopovskaya.ingoodhands.entity.db.UserDB;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
public class ShelterOwner extends ShelterEmployee {

    @Getter
    @Setter
    @NotNull
    private Shelter ownedShelter;

    public ShelterOwner(int id, String firstName, String lastName, String login, String password, UserRole role) {
        super(id, firstName, lastName, login, password, role);
    }

    public ShelterOwner(User user) {
        super(user.getId(), user.getFirstName(), user.getLastName(), user.getLogin(), user.getPassword(), user.getRole());
    }

    public ShelterOwner(UserDB user) {
        super(user.getId(), user.getFirstName(), user.getLastName(), user.getLogin(), user.getPassword(), user.getRole());
        this.ownedShelter = user.getShelter();
    }


}
