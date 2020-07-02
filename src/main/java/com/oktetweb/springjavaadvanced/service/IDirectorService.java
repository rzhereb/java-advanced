package com.oktetweb.springjavaadvanced.service;

import com.oktetweb.springjavaadvanced.model.Director;

import java.util.List;

public interface IDirectorService {

    Director insertDirector(Director director);

    List<Director> getAllDirectors();

}
