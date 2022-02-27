package com.adrianmensing.controller.config;

import com.adrianmensing.domain.User;
import com.adrianmensing.domain.UserRepository;
import com.adrianmensing.security.AuthTokenService;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.logging.Logger;
import java.util.stream.Stream;

import static com.adrianmensing.security.AuthTokenService.AUTH_TOKEN_NAME;

@Component
public class UserAuthorizationTokenInterceptor implements HandlerInterceptor {

    private static final Logger logger = Logger.getLogger(UserAuthorizationTokenInterceptor.class.getName());

    @Resource
    private UserRepository userRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // TODO: add a lru cache when shit hits the fan.
        Stream<Cookie> stream = request.getCookies() == null ? Stream.empty() : Arrays.stream(request.getCookies());
        User user = stream.filter(cookie -> cookie.getName()
                                                   .equals(AUTH_TOKEN_NAME))
                           .findFirst()
                           .flatMap(cookie -> userRepository.findUserByTokenValue(cookie.getValue()))
                           .orElseGet(() -> userRepository.save(new User()));

        Cookie authTokenCookie = AuthTokenService.fromToken(user.getToken().getValue());
        response.addCookie(authTokenCookie);

        logger.info(String.format("Page accessed with user token %s", user.getToken()));

        return true;
    }
}
