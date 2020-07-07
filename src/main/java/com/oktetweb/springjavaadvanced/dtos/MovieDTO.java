package com.oktetweb.springjavaadvanced.dtos;

import com.oktetweb.springjavaadvanced.model.Movie;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class MovieDTO {

    private List<Movie> movies;
    private int totalElements;
    private int pagesCount;
    private boolean isLast;
    private boolean isEmpty;

}
