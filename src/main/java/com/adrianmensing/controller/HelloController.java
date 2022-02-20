package com.adrianmensing.controller;

import com.adrianmensing.security.AuthTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;

import static com.adrianmensing.security.AuthTokenService.AUTH_TOKEN_NAME;

@Controller
public class HelloController {

    private final AuthTokenService authTokenService;

    public HelloController(@Autowired AuthTokenService service) {
        this.authTokenService = service;
    }

    @GetMapping("/")
    public String index(@CookieValue(value = AUTH_TOKEN_NAME, required = false) String token, HttpServletResponse response) {
        authTokenService.getUserId(token, response);
        return "hello";
    }
}
