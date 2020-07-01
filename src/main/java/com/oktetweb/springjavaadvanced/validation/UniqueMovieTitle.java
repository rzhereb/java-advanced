package com.oktetweb.springjavaadvanced.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueMovieTitleValidator.class)
public @interface UniqueMovieTitle {

    String message() default "Movie title should be unique";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
