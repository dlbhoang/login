package com.vnpt.shopguard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vnpt.shopguard.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
