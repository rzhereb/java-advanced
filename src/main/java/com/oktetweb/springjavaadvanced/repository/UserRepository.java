package com.oktetweb.springjavaadvanced.repository;

import com.oktetweb.springjavaadvanced.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);
}
