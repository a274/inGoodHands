package com.khlopovskaya.ingoodhands.entity.model.user;

import com.khlopovskaya.ingoodhands.entity.db.TestResult;
import com.khlopovskaya.ingoodhands.entity.db.UserDB;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class PetOwner extends Sponsor {

    @Getter
    @Setter
    private TestResult testResult;

    public PetOwner(int id, String firstName, String lastName, String login, String password, UserRole role) {
        super(id, firstName, lastName, login, password, role);
    }

    public PetOwner(User user) {
        super(user.getId(), user.getFirstName(), user.getLastName(), user.getLogin(), user.getPassword(), user.getRole());
    }

    public PetOwner(UserDB user) {
        super(user.getId(), user.getFirstName(), user.getLastName(), user.getLogin(), user.getPassword(), user.getRole());
        this.testResult = user.getResult();
    }

}
