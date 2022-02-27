package com.adrianmensing.security;

import com.adrianmensing.domain.AuthToken;

/**
 * A simple way of managing user tokens.
 *
 * <p>no idea what the fuck spring-boot is on about.</p>
 */
@Deprecated
class KeyTokenPairImpl {
    private static final int TOKEN_LENGTH = 64;

    public final String secretToken;
    public final String id;

    public KeyTokenPairImpl(String secretToken, String userId) {
        this.secretToken = secretToken;
        this.id = userId;
    }

    public static KeyTokenPairImpl newKeyTokenPair(int userId) {
        String    token     = TokenGenerator.generateToken(TOKEN_LENGTH);
        AuthToken authToken = new AuthToken(token);
        System.out.println(authToken);

        String id = Integer.toString(userId);

        return new KeyTokenPairImpl(token, id);
    }
}