package com.oktetweb.springjavaadvanced.controller;

import com.oktetweb.springjavaadvanced.dtos.MovieDTO;
import com.oktetweb.springjavaadvanced.model.Movie;
import com.oktetweb.springjavaadvanced.service.IMovieService;
import com.oktetweb.springjavaadvanced.validation.MovieValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping(value = "/movies")
public class MovieController {

    @Autowired
    private IMovieService movieService;

    @Autowired
    private MovieValidator movieValidator;

    @RequestMapping(method = RequestMethod.GET)
    public MovieDTO getMovies(@RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "3") int size,
                              HttpServletRequest request,
                              Principal principal) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return movieService.getMovies(pageRequest);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/{directorId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Movie createMovie(@RequestBody @Valid Movie movie, @PathVariable Integer directorId) {
        return movieService.insertMovie(movie, directorId);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Movie updateMovie(@RequestBody Movie movie, @PathVariable Integer id) {
//        Optional<Movie> movieOptional = movies.stream()
//                .filter(movie1 -> movie1.getId() == id)
//                .findFirst();
//        if (movieOptional.isPresent()) {
//            Movie movieFromList = movieOptional.get();
//            movies.set(movies.indexOf(movieFromList), movie);
//        } else {
//            movie.setId(id);
//            movies.add(movie);
//        }
//        return movie;
        movie.setId(id);
        return movieService.updateMovie(movie);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMovie(@PathVariable Integer id) {
//        Optional<Movie> movieOptional = movies.stream()
//                .filter(movie1 -> movie1.getId() == id)
//                .findFirst();
//        movieOptional.ifPresent(movie -> movies.remove(movie));

        movieService.deleteMovie(id);
    }

    @InitBinder
    private void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(movieValidator);
    }
}
