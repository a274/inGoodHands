package com.khlopovskaya.ingoodhands.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AuthData {
    private String username;
    private String password;
}
