package com.oktetweb.springjavaadvanced.controller;

import com.oktetweb.springjavaadvanced.dtos.MovieDTO;
import com.oktetweb.springjavaadvanced.model.Movie;
import com.oktetweb.springjavaadvanced.service.IMovieService;
import com.oktetweb.springjavaadvanced.validation.MovieValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;

@EnableAutoConfiguration(exclude = {HibernateJpaAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class,
        DataSourceAutoConfiguration.class})
@WebMvcTest(MovieController.class)
@ContextConfiguration(classes = MovieController.class)
public class MovieControllerTest {

    @MockBean
    private IMovieService movieService;
    @MockBean
    private MovieValidator movieValidator;

    @Autowired
    private MovieController movieController;

    @Autowired
    private MockMvc mockMvc;


    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(movieController).build();
    }

    @Test
    public void getMoviesWithoutParametersMustRespondWithMovieList() throws Exception {

        Movie movie = new Movie();
        movie.setId(1);
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setTotalElements(1);
        movieDTO.setMovies(Arrays.asList(movie));

        Mockito.when(movieService.getMovies(any(PageRequest.class))).thenReturn(movieDTO);
//        BDDMockito.given(movieService.getMovies(any(PageRequest.class))).willReturn(movieDTO);

        mockMvc.perform(MockMvcRequestBuilders.get("/movies").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.movies[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalElements").value(1));
    }
}
