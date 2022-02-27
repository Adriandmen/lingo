package com.adrianmensing.controller.security;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

@Service
public class AuthTokenService {
    public static final String AUTH_TOKEN_NAME = "_TK";

    private final   Logger              logger;
    private final   Map<String, String> authTokenUserMap; // TODO: move this crap into data storage service?
    protected final AtomicInteger       userId;

    public AuthTokenService() {
        this.authTokenUserMap = new HashMap<>();
        this.userId = new AtomicInteger(1); // TODO: get from database service.
        this.logger = Logger.getLogger(getClass().getName());
    }

    @Deprecated
    public Cookie newAuthCookie() {
        KeyTokenPairImpl pair = KeyTokenPairImpl.newKeyTokenPair(userId.getAndIncrement());
        authTokenUserMap.put(pair.secretToken, pair.id);
        logger.info(String.format("User ID: [token: %s] -> [id: %s]", pair.secretToken, pair.id));

        return fromToken(pair.secretToken);
    }

    public static Cookie fromToken(@NonNull String token) {
        Cookie cookie = new Cookie(AUTH_TOKEN_NAME, token);
        cookie.setMaxAge(60 * 60 * 24 * 365);
        cookie.setSecure(true);
        cookie.setHttpOnly(true);

        return cookie;
    }
}
