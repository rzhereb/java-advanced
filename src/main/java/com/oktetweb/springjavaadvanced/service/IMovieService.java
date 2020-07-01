package com.oktetweb.springjavaadvanced.service;

import com.oktetweb.springjavaadvanced.model.Movie;

import java.util.List;

public interface IMovieService {

    void deleteMovie(Integer id);

    List<Movie> getAllMovies();

    Movie insertMovie(Movie movie);

    Movie updateMovie(Movie movie);
}
