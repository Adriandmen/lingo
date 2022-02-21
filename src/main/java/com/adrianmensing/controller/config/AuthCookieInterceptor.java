package com.adrianmensing.controller.config;

import com.adrianmensing.security.AuthTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

import static com.adrianmensing.security.AuthTokenService.AUTH_TOKEN_NAME;

@Component
public class AuthCookieInterceptor implements HandlerInterceptor {

    private final AuthTokenService authTokenService;

    public AuthCookieInterceptor(@Autowired AuthTokenService service) {
        this.authTokenService = service;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // Find the auth cookie or create a new one.
        Cookie authCookie = Arrays.stream(request.getCookies())
                                  .filter(cookie -> cookie.getName()
                                                          .equals(AUTH_TOKEN_NAME))
                                  .findFirst()
                                  .map(authTokenService::validateOrCreateNewAuthCookie)
                                  .orElseGet(authTokenService::newAuthCookie);

        response.addCookie(authCookie);
        return true;
    }
}
