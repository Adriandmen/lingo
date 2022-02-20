package com.adrianmensing.security;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class AuthTokenService {
    public static final    String AUTH_TOKEN_NAME = "_TK";
    protected static final int    TOKEN_LENGTH    = 64;

    private final   Map<String, String> authTokenUserMap; // TODO: move this crap into data storage service?
    protected final AtomicInteger       userId;

    public AuthTokenService() {
        this.authTokenUserMap = new HashMap<>();
        this.userId = new AtomicInteger(1); // TODO: get from database service.
    }

    public String getUserId(@Nullable String token, HttpServletResponse response) {
        KeyTokenPair pair   = computeIfAbsent(token);
        Cookie       cookie = fromToken(pair.secretToken);

        authTokenUserMap.put(pair.secretToken, pair.id);
        response.addCookie(cookie);

        Logger.getLogger(getClass().getName())
              .log(Level.INFO, String.format("User ID: [token: %s] -> [id: %s]", pair.secretToken, pair.id));

        return pair.id;
    }

    private KeyTokenPair computeIfAbsent(String token) {
        if (authTokenUserMap.containsKey(token))
            return new KeyTokenPair(token, authTokenUserMap.get(token));

        return KeyTokenPair.newKeyTokenPair(userId.getAndIncrement());
    }

    public static Cookie fromToken(String token) {
        Cookie cookie = new Cookie(AUTH_TOKEN_NAME, token);
        cookie.setMaxAge(60 * 60 * 24 * 365);
        cookie.setSecure(true);
        cookie.setHttpOnly(true);

        return cookie;
    }
}
