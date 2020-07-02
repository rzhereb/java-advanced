package com.oktetweb.springjavaadvanced.validation;

import com.oktetweb.springjavaadvanced.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueMovieTitleValidator implements ConstraintValidator<UniqueMovieTitle, String> {


    // DO NOT USE SPRING BEANS IN VALIDATORS!
    @Autowired
    private MovieRepository movieRepository;

    @Override
    public boolean isValid(String title, ConstraintValidatorContext context) {
        return true; //change
    }
}
