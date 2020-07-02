package com.oktetweb.springjavaadvanced.service;

import com.oktetweb.springjavaadvanced.model.Director;
import com.oktetweb.springjavaadvanced.repository.DirectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DirectorService implements IDirectorService {

    @Autowired
    private DirectorRepository directorRepository;

    @Override
    public Director insertDirector(Director director) {
        return directorRepository.save(director);
    }

    @Override
    public List<Director> getAllDirectors() {
        return directorRepository.findAll();
    }
}
