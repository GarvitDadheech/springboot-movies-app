package com.movieapp.service;

import com.movieapp.model.Director;
import com.movieapp.model.Movie;
import com.movieapp.repository.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class MovieServiceTest {

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieServiceImpl movieService;

    private Movie movie1;
    private Movie movie2;
    private Director director;

    @BeforeEach
    void setUp() {
        director = Director.builder()
                .id(1L)
                .firstName("Test")
                .lastName("Director")
                .dateOfBirth(LocalDate.of(1980, 1, 1))
                .nationality("Test Nationality")
                .biography("Test Biography")
                .build();
        
        movie1 = Movie.builder()
                .id(1L)
                .title("Test Movie 1")
                .releaseDate(LocalDate.of(2020, 1, 1))
                .description("Test Description 1")
                .genre("Action")
                .durationMinutes(120)
                .rating(8.5)
                .director(director)
                .build();
        
        movie2 = Movie.builder()
                .id(2L)
                .title("Test Movie 2")
                .releaseDate(LocalDate.of(2021, 1, 1))
                .description("Test Description 2")
                .genre("Comedy")
                .durationMinutes(110)
                .rating(7.5)
                .director(director)
                .build();
    }

    @Test
    public void shouldReturnAllMovies() {
        // Given
        given(movieRepository.findAll()).willReturn(Arrays.asList(movie1, movie2));
        
        // When
        List<Movie> movies = movieService.findAllMovies();
        
        // Then
        assertThat(movies).hasSize(2);
        assertThat(movies.get(0).getTitle()).isEqualTo("Test Movie 1");
        assertThat(movies.get(1).getTitle()).isEqualTo("Test Movie 2");
        verify(movieRepository, times(1)).findAll();
    }
    
    @Test
    public void shouldReturnAllMoviesWithDirectors() {
        // Given
        given(movieRepository.findAllMoviesWithDirectors()).willReturn(Arrays.asList(movie1, movie2));
        
        // When
        List<Movie> movies = movieService.findAllMoviesWithDirectors();
        
        // Then
        assertThat(movies).hasSize(2);
        assertThat(movies.get(0).getTitle()).isEqualTo("Test Movie 1");
        assertThat(movies.get(0).getDirector().getFirstName()).isEqualTo("Test");
        assertThat(movies.get(1).getTitle()).isEqualTo("Test Movie 2");
        assertThat(movies.get(1).getDirector().getFirstName()).isEqualTo("Test");
        verify(movieRepository, times(1)).findAllMoviesWithDirectors();
    }
    
    @Test
    public void shouldReturnMovieById() {
        // Given
        given(movieRepository.findById(1L)).willReturn(Optional.of(movie1));
        
        // When
        Optional<Movie> foundMovie = movieService.findMovieById(1L);
        
        // Then
        assertThat(foundMovie).isPresent();
        assertThat(foundMovie.get().getTitle()).isEqualTo("Test Movie 1");
        assertThat(foundMovie.get().getDirector().getFirstName()).isEqualTo("Test");
        verify(movieRepository, times(1)).findById(1L);
    }
    
    @Test
    public void shouldSaveMovie() {
        // Given
        given(movieRepository.save(any(Movie.class))).willReturn(movie1);
        
        // When
        Movie savedMovie = movieService.saveMovie(movie1);
        
        // Then
        assertThat(savedMovie.getId()).isEqualTo(1L);
        assertThat(savedMovie.getTitle()).isEqualTo("Test Movie 1");
        verify(movieRepository, times(1)).save(movie1);
    }
    
    @Test
    public void shouldDeleteMovie() {
        // When
        movieService.deleteMovie(1L);
        
        // Then
        verify(movieRepository, times(1)).deleteById(1L);
    }
} 