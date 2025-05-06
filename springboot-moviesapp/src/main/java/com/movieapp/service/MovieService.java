package com.movieapp.service;

import com.movieapp.model.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieService {
    List<Movie> findAllMovies();
    List<Movie> findAllMoviesWithDirectors();
    Optional<Movie> findMovieById(Long id);
    Movie saveMovie(Movie movie);
    void deleteMovie(Long id);
} 