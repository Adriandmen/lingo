package com.adrianmensing.controller.rest;

import com.adrianmensing.domain.User;
import com.adrianmensing.domain.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class UserController {

    @Resource
    private UserRepository userRepository;

    @GetMapping("/api/user")
    public ResponseEntity<User> getUserInfo(@RequestParam("token") String token) {
        return ResponseEntity.of(userRepository.findUserByTokenValue(token));
    }

    @PutMapping("/api/user/name")
    public ResponseEntity<?> changeUserName(@RequestParam("token") String token, @RequestParam("username") String username) {
        User user = userRepository.getUserByTokenValue(token);

        if (user == null)
            return ResponseEntity.notFound()
                                 .build();

        user.setUsername(username);
        return ResponseEntity.ok(null);
    }
}
