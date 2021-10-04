package com.ab.springbootreactbackend.service.impl;

import com.ab.springbootreactbackend.model.User;
import com.ab.springbootreactbackend.repository.UserRepository;
import com.ab.springbootreactbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    PasswordEncoder passwordEncoder;

    @Override
    public void createUser(User user) {
        passwordEncoder = new BCryptPasswordEncoder();
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        userRepository.save(user);

    }
}
