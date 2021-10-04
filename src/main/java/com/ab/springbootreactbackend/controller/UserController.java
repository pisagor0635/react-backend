package com.ab.springbootreactbackend.controller;

import com.ab.springbootreactbackend.model.User;
import com.ab.springbootreactbackend.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public void createUser(@RequestBody User user) {
        userService.createUser(user);
    }
}
