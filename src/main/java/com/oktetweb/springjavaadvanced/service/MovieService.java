package com.oktetweb.springjavaadvanced.service;

import com.oktetweb.springjavaadvanced.dtos.MovieDTO;
import com.oktetweb.springjavaadvanced.model.Movie;
import com.oktetweb.springjavaadvanced.repository.DirectorRepository;
import com.oktetweb.springjavaadvanced.repository.MovieRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@Service
public class MovieService implements IMovieService {

    private MovieRepository movieRepository;

    private DirectorRepository directorRepository;

    @Value("${file.upload.location:uploads}")
    private String uploadFolderLocation;

    private Path uploadFolder;


    @Autowired
    public MovieService(MovieRepository movieRepository, DirectorRepository directorRepository) {
        this.movieRepository = movieRepository;
        this.directorRepository = directorRepository;
    }

    @PostConstruct
    public void createUploadFolder() {
        this.uploadFolder = Paths.get(uploadFolderLocation).toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.uploadFolder);
        } catch (IOException e) {
            log.error("Unable to create folder {}", this.uploadFolder.toString());
        }
    }

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
    public Movie insertMovie(Movie movie, MultipartFile file, int directorId) {
        if (movieRepository.findByTitle(movie.getTitle()).isPresent()) {
            log.info("Movie with title "+ movie.getTitle() + " exists in database");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This movie title already exists!");
        }
        directorRepository.findById(directorId).ifPresent(director -> {
//            movie.setDirector(director);
            movieRepository.save(movie);
        });

        try {
            if (!file.isEmpty()) {
                String originalFilename = file.getOriginalFilename();
                String newFileName = movie.getTitle() + originalFilename.substring(originalFilename.indexOf("."));
                Path targetPath = uploadFolder.resolve(newFileName);
                Files.copy(file.getInputStream(), targetPath);
            }
        } catch (Exception ex) {

        }
        return movie;
    }

    @Override
    public Movie updateMovie(Movie movie) {
        return movieRepository.save(movie);
    }
}
