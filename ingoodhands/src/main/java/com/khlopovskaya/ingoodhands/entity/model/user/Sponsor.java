package com.khlopovskaya.ingoodhands.entity.model.user;

import com.khlopovskaya.ingoodhands.entity.db.UserDB;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Sponsor extends User {

    public Sponsor(int id, String firstName, String lastName, String login, String password, UserRole role) {
        super(id, firstName, lastName, login, password, role);
    }

    public Sponsor(User user) {
        super(user.getId(), user.getFirstName(), user.getLastName(), user.getLogin(), user.getPassword(), user.getRole());
    }

    public Sponsor(UserDB user) {
        super(user.getId(), user.getFirstName(), user.getLastName(), user.getLogin(), user.getPassword(), user.getRole());
    }

}
