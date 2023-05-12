package com.khlopovskaya.ingoodhands.mapper;

import com.khlopovskaya.ingoodhands.entity.model.*;

public class UserFactory {

    public AbstractUser createUser(AbstractUser abstractUser) {
        return switch (abstractUser.getRole()) {
            case PET_OWNER -> new PetOwner();
            case OWNER -> new ShelterOwner();
            case SPONSOR -> new Sponsor();
            case EMPLOYEE -> new ShelterEmployee();
        };
    }


}
