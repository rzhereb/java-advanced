package com.oktetweb.springjavaadvanced.model;

import com.oktetweb.springjavaadvanced.validation.UniqueMovieTitle;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    @NotBlank
    @UniqueMovieTitle
    private String title;
    @NotBlank
    private String description;
    @Positive
    private int duration;

}
