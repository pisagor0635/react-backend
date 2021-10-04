package com.ab.springbootreactbackend.controller;

import com.ab.springbootreactbackend.error.ApiError;
import com.ab.springbootreactbackend.model.User;
import com.ab.springbootreactbackend.service.UserService;
import com.ab.springbootreactbackend.util.GenericResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/users")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody User user) {

        ApiError error = new ApiError(400, "Validation Error", "api/v1/users/create");
        Map<String, String> validationErrors = new HashMap<>();
        String userName = user.getUsername();
        if (userName == null || userName.length() == 0) {
            validationErrors.put("username", "username cannot be null");
            error.setValidationErrors(validationErrors);
        }
        String displayName = user.getDisplayName();
        if (displayName == null || displayName.length() == 0) {
            validationErrors.put("displayName", "cannot be null");
            error.setValidationErrors(validationErrors);
        }

        if (validationErrors.size() > 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }

        userService.createUser(user);
        return ResponseEntity.ok(new GenericResponse("user created"));
    }
}
