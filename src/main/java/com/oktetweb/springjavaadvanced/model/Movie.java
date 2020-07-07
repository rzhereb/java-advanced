package com.oktetweb.springjavaadvanced.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.oktetweb.springjavaadvanced.validation.UniqueMovieTitle;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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

    @ManyToOne(targetEntity = Director.class, optional = false, cascade = CascadeType.PERSIST)
    //joincolumn in that case is optional
    @JsonIgnore
    private Director director;

}
