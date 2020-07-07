package com.oktetweb.springjavaadvanced.service;

import com.oktetweb.springjavaadvanced.dtos.MovieDTO;
import com.oktetweb.springjavaadvanced.model.Movie;
import com.oktetweb.springjavaadvanced.repository.DirectorRepository;
import com.oktetweb.springjavaadvanced.repository.MovieRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@Service
public class MovieService implements IMovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private DirectorRepository directorRepository;

    @Override
    public void deleteMovie(Integer id) {
        movieRepository.deleteById(id);
    }

    @Override
    public MovieDTO getMovies(PageRequest pageRequest) {
        Page<Movie> moviePages = movieRepository.findAll(pageRequest);
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setMovies(moviePages.getContent());
        movieDTO.setEmpty(moviePages.isEmpty());
        movieDTO.setLast(moviePages.isLast());
        movieDTO.setPagesCount(moviePages.getTotalPages());
        movieDTO.setTotalElements(moviePages.getNumberOfElements());
        return movieDTO;
    }

    @Override
    public Movie insertMovie(Movie movie, int directorId) {
        if (movieRepository.findByTitle(movie.getTitle()).isPresent()) {
            log.info("Movie with title "+ movie.getTitle() + " exists in database");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This movie title already exists!");
        }
        directorRepository.findById(directorId).ifPresent(director -> {
//            movie.setDirector(director);
            movieRepository.save(movie);
        });
        return movie;
    }

    @Override
    public Movie updateMovie(Movie movie) {
        return movieRepository.save(movie);
    }
}
