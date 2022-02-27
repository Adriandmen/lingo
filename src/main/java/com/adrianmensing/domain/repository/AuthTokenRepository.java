package com.adrianmensing.domain.repository;

import com.adrianmensing.domain.entity.AuthToken;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AuthTokenRepository extends CrudRepository<AuthToken, Long> {
    Optional<AuthToken> findAuthTokenByValue(String value);
}
