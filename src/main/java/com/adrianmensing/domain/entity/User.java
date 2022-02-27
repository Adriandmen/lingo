package com.adrianmensing.domain.entity;

import com.adrianmensing.controller.security.TokenGenerator;
import org.springframework.lang.Nullable;

import javax.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Nullable
    private String username;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private AuthToken token;

    public User() {
        this(new AuthToken(TokenGenerator.generateToken(32)));
    }

    /**
     * Protected constructor for User instances.
     *
     * <p>Should not be used outside of this package's scope.</p>
     *
     * @param token The token associated with this user.
     */
    protected User(AuthToken token) {
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
