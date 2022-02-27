package com.adrianmensing.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findUserByTokenValue(String value);

    @Deprecated
    Optional<User> findUserByToken(AuthToken token);

    User getUserByTokenValue(String value);
}
