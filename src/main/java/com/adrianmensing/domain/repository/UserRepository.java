package com.adrianmensing.domain.repository;

import com.adrianmensing.domain.entity.AuthToken;
import com.adrianmensing.domain.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findUserByTokenValue(String value);

    @Deprecated
    Optional<User> findUserByToken(AuthToken token);

    User getUserByTokenValue(String value);
}
