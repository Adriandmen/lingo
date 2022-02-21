package com.adrianmensing.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class AuthToken {

    @Id
    @GeneratedValue
    private Long id;

    private String token;

    protected AuthToken() {
    }

    public AuthToken(String token) {
        this.token = token;
    }

    public Long getId() {
        return id;
    }

    public String getToken() {
        return token;
    }
}
