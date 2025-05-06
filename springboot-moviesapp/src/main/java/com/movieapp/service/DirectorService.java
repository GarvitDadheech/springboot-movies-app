package com.movieapp.service;

import com.movieapp.model.Director;

import java.util.List;
import java.util.Optional;

public interface DirectorService {
    List<Director> findAllDirectors();
    Optional<Director> findDirectorById(Long id);
    Director saveDirector(Director director);
    void deleteDirector(Long id);
} 