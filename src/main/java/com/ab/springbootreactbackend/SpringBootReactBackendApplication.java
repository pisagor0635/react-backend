package com.ab.springbootreactbackend;

import com.ab.springbootreactbackend.model.User;
import com.ab.springbootreactbackend.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class SpringBootReactBackendApplication {

    public static void main(String[] args) {

        SpringApplication.run(SpringBootReactBackendApplication.class, args);
    }

    @Bean
    CommandLineRunner createInitialUsers(UserService userService){

        return (args) -> {
            User user = new User();
            user.setUsername("username");
            user.setDisplayName("Display");
            user.setPassword("P4ssword");

            userService.createUser(user);
        };
    }

}
