package com.oktetweb.springjavaadvanced.controller;

import com.oktetweb.springjavaadvanced.model.Director;
import com.oktetweb.springjavaadvanced.service.IDirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/director")
public class DirectorController {

    @Autowired
    private IDirectorService directorService;

    @GetMapping
    public List<Director> getDirectors() {
        return directorService.getAllDirectors();
    }

    @PostMapping
    public Director createDirector(@RequestBody Director director) {
        return directorService.insertDirector(director);
    }
}
