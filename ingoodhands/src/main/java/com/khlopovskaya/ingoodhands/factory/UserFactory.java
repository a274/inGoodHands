package com.khlopovskaya.ingoodhands.factory;

import com.khlopovskaya.ingoodhands.entity.db.UserDB;
import com.khlopovskaya.ingoodhands.entity.model.user.*;
import org.springframework.stereotype.Component;

@Component
public class UserFactory {

    public User createUser(User user) {
        if (user != null) return switch (user.getRole()) {
            case PET_OWNER -> new PetOwner(user);
            case OWNER -> new ShelterOwner(user);
            case SPONSOR -> new Sponsor(user);
            case EMPLOYEE -> new ShelterEmployee(user);
        };
        return null;
    }

    public User createUser(UserDB user) {
        if (user != null) return switch (user.getRole()) {
            case PET_OWNER -> new PetOwner(user);
            case OWNER -> new ShelterOwner(user);
            case SPONSOR -> new Sponsor(user);
            case EMPLOYEE -> new ShelterEmployee(user);
        };
        return null;
    }


}
