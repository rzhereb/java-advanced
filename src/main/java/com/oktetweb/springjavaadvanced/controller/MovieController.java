package com.oktetweb.springjavaadvanced.controller;

import com.oktetweb.springjavaadvanced.model.Movie;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController(value = "/movies")
public class MovieController {

    private List<Movie> movies = new ArrayList<>();
    {
        movies.add(new Movie(1, "LOTR: ROTK", "3rd movie LOTR"));
        movies.add(new Movie(2, "Harry Potter GoF", "4th movie HP"));
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Movie> getMovies() {
        return movies;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Movie createMovie(@RequestBody Movie movie) {
        movies.add(movie);
        return movie;
    }
}
