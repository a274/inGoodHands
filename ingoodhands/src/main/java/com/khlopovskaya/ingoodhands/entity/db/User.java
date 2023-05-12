package com.khlopovskaya.ingoodhands.entity.db;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "user_")
public class User implements UserDetails {

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
    private TestResult result;

    @OneToMany
    private List<Pet> favouritePets;

    @OneToOne
    @JoinColumn(name = "shelter_shelter_id")
    private Shelter shelter;

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
}