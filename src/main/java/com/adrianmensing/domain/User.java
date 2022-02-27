package com.adrianmensing.domain;

import com.adrianmensing.security.TokenGenerator;
import org.springframework.lang.Nullable;

import javax.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Nullable
    private String username;

    @OneToOne(cascade = CascadeType.ALL)
    private AuthToken token;

    public User() {
        this(new AuthToken(TokenGenerator.generateToken(32)));
    }

    public User(AuthToken token) {
        this.token = token;
    }

    public Long getId() {
        return id;
    }

    @Nullable
    public String getUsername() {
        return username;
    }

    public void setUsername(@Nullable String username) {
        this.username = username;
    }

    public AuthToken getToken() {
        return token;
    }
}
