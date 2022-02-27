package com.adrianmensing;

import com.adrianmensing.domain.AuthTokenRepository;
import com.adrianmensing.domain.User;
import com.adrianmensing.domain.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.logging.Logger;

@SpringBootApplication
public class RootApplication {

    private static final Logger logger = Logger.getLogger(RootApplication.class.getName());

    public static void main(String[] args) {
        SpringApplication.run(RootApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(UserRepository userRepository, AuthTokenRepository authTokenRepository) {
        return (args) -> {
            logger.info("User count: " + userRepository.count());

            User user = new User();

            userRepository.save(user);

            logger.info("Count: " + authTokenRepository.count());
        };
    }
}
