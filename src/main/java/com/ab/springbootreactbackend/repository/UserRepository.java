package com.ab.springbootreactbackend.repository;

import com.ab.springbootreactbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    User findByUsername(String username);
}
