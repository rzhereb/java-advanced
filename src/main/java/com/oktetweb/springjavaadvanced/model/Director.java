package com.oktetweb.springjavaadvanced.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Entity
public class Director {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @JsonFormat(pattern = "dd-MMM-yyyy")
    private LocalDate birthDate;

    @OneToMany(targetEntity = Movie.class, mappedBy = "director")
    private List<Movie> movies;
}
