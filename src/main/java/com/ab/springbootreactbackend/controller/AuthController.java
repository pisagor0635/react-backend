package com.ab.springbootreactbackend.controller;

import com.ab.springbootreactbackend.error.ApiError;
import com.ab.springbootreactbackend.model.User;
import com.ab.springbootreactbackend.repository.UserRepository;
import com.ab.springbootreactbackend.util.Views;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/users")
public class AuthController {
    @Autowired
    UserRepository userRepository;
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();;

    @PostMapping("/auth")
    @JsonView(Views.Base.class)
    public ResponseEntity<?> handleAuthentication(@RequestHeader(name = "Authorization", required = false) String authorization) {
        if (authorization == null) {
            ApiError error = new ApiError(401, "Unauthorized request", "/api/v1/users/auth");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
        }
        String base64encoded = authorization.split("Basic ")[1];
        String decoded = new String(Base64.getDecoder().decode(base64encoded));
        String[] infoParts = decoded.split(":");
        String userName = infoParts[0];
        String password = infoParts[1];

        User userInDB = userRepository.findByUsername(userName);
        if (userInDB == null) {
            ApiError error = new ApiError(401, "Unauthorized request", "/api/v1/users/auth");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
        }

        String hashedPassword = userInDB.getPassword();

        if (!passwordEncoder.matches(password, hashedPassword)) {
            ApiError error = new ApiError(401, "Unauthorized request", "/api/v1/users/auth");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
        }

        return ResponseEntity.ok().body(userInDB);

    }

}
