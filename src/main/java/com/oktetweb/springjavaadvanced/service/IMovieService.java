package com.oktetweb.springjavaadvanced.service;

import com.oktetweb.springjavaadvanced.dtos.MovieDTO;
import com.oktetweb.springjavaadvanced.model.Movie;
import org.springframework.data.domain.PageRequest;

public interface IMovieService {

    void deleteMovie(Integer id);

    MovieDTO getMovies(PageRequest pageRequest);

    Movie insertMovie(Movie movie, int directorId);

    Movie updateMovie(Movie movie);
}
