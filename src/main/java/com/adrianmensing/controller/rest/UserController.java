package com.adrianmensing.controller.rest;

import com.adrianmensing.data.ChangeUsernameRequest;
import com.adrianmensing.domain.entity.User;
import com.adrianmensing.domain.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import static com.adrianmensing.controller.ResponseCommons.STATUS_OK;

@RestController
public class UserController {

    @Resource
    private UserRepository userRepository;

    @GetMapping("/api/user")
    public ResponseEntity<User> getUserInfo(@RequestHeader("Authorization") String token) {
        return ResponseEntity.of(userRepository.findUserByTokenValue(token));
    }

    @PutMapping("/api/user/name")
    public ResponseEntity<?> changeUserName(@RequestHeader("Authorization") String token, @RequestBody ChangeUsernameRequest request) {
        User user = userRepository.getUserByTokenValue(token);

        if (user == null)
            return ResponseEntity.notFound()
                                 .build();

        user.setUsername(request.getUsername());
        userRepository.save(user);

        return STATUS_OK;
    }
}
