package com.oktetweb.springjavaadvanced.repository;

import com.oktetweb.springjavaadvanced.model.Director;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DirectorRepository extends JpaRepository<Director, Integer> {
}
