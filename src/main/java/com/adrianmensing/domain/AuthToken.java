package com.adrianmensing.domain;

import javax.persistence.*;
import java.util.StringJoiner;

@Entity
public class AuthToken {

    @Id
    @GeneratedValue
    private Long id;

    private String value;

    protected AuthToken() {
    }

    public AuthToken(String value) {
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", AuthToken.class.getSimpleName() + "[", "]").add("id=" + id)
                                                                                 .add("value='" + value + "'")
                                                                                 .toString();
    }
}
