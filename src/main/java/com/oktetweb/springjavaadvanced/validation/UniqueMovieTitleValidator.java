package com.oktetweb.springjavaadvanced.validation;

import com.oktetweb.springjavaadvanced.repository.MovieRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueMovieTitleValidator implements ConstraintValidator<UniqueMovieTitle, String> {

    private MovieRepository movieRepository;

    @Override
    public boolean isValid(String title, ConstraintValidatorContext context) {
        return true; //change
    }
}
