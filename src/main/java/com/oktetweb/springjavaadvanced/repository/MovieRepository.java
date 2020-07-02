package com.oktetweb.springjavaadvanced.repository;

import com.oktetweb.springjavaadvanced.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Integer> {

    Optional<Movie> findByTitle(String title);

}
