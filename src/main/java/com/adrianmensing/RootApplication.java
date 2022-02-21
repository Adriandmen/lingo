package com.adrianmensing;

import com.adrianmensing.domain.AuthToken;
import com.adrianmensing.domain.AuthTokenRepository;
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
    public CommandLineRunner demo(AuthTokenRepository repository) {
        return (args) -> {
            repository.save(new AuthToken("abcdefg"));
            repository.save(new AuthToken("joqweqjwe"));

            logger.info("Count: " + repository.count());
        };
    }
}
