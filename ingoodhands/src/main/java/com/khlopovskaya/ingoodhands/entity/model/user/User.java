package com.khlopovskaya.ingoodhands.entity.model.user;

import com.khlopovskaya.ingoodhands.entity.db.PetDB;
import com.khlopovskaya.ingoodhands.entity.db.Shelter;
import com.khlopovskaya.ingoodhands.entity.db.UserDB;
import com.khlopovskaya.ingoodhands.entity.model.pet.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {
    @Serial
    private static final long serialVersionUID = 9075411076908200384L;
    protected int id;
    protected String firstName;
    protected String lastName;
    protected String login;
    protected String password;
    private UserRole role;

    public List<PetDB> getAllPets() {
        return null;
    }

    public List<PetDB> getAllPets(int age,
                                  PetCharacter character,
                                  PetColour colour,
                                  PetSpecies species,
                                  PetSize size,
                                  PetGender gender) {
        return null;
    }

    public List<Shelter> getAllShelters() {
        return null;
    }

    public void donate(Shelter shelter, double sum) {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public UserDB toUserDB() {
        UserDB userDB = new UserDB();
        userDB.setId(this.getId());
        userDB.setLogin(this.getLogin());
        userDB.setFirstName(this.getFirstName());
        userDB.setLastName(this.getLastName());
        userDB.setRole(this.getRole());
        userDB.setPassword(this.getPassword());
        return userDB;
    }
}
