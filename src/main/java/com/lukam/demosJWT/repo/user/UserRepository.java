package com.lukam.demosJWT.repo.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lukam.demosJWT.user.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);
    
}
