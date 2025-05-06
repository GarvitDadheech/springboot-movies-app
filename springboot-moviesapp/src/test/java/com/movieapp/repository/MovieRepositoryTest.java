package com.movieapp.repository;

import com.movieapp.model.Director;
import com.movieapp.model.Movie;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class MovieRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private MovieRepository movieRepository;

    @Test
    public void shouldFindAllMovies() {
        // Given
        Director director = Director.builder()
                .firstName("Test")
                .lastName("Director")
                .dateOfBirth(LocalDate.of(1980, 1, 1))
                .nationality("Test Nationality")
                .biography("Test Biography")
                .build();
        
        entityManager.persist(director);
        
        Movie movie1 = Movie.builder()
                .title("Test Movie 1")
                .releaseDate(LocalDate.of(2020, 1, 1))
                .description("Test Description 1")
                .genre("Action")
                .durationMinutes(120)
                .rating(8.5)
                .director(director)
                .build();
        
        Movie movie2 = Movie.builder()
                .title("Test Movie 2")
                .releaseDate(LocalDate.of(2021, 1, 1))
                .description("Test Description 2")
                .genre("Comedy")
                .durationMinutes(110)
                .rating(7.5)
                .director(director)
                .build();
        
        entityManager.persist(movie1);
        entityManager.persist(movie2);
        entityManager.flush();
        
        // When
        List<Movie> movies = movieRepository.findAll();
        
        // Then
        assertThat(movies).hasSize(2);
        assertThat(movies.get(0).getTitle()).isEqualTo("Test Movie 1");
        assertThat(movies.get(1).getTitle()).isEqualTo("Test Movie 2");
    }
    
    @Test
    public void shouldFindAllMoviesWithDirectors() {
        // Given
        Director director = Director.builder()
                .firstName("Test")
                .lastName("Director")
                .dateOfBirth(LocalDate.of(1980, 1, 1))
                .nationality("Test Nationality")
                .biography("Test Biography")
                .build();
        
        entityManager.persist(director);
        
        Movie movie = Movie.builder()
                .title("Test Movie")
                .releaseDate(LocalDate.of(2020, 1, 1))
                .description("Test Description")
                .genre("Action")
                .durationMinutes(120)
                .rating(8.5)
                .director(director)
                .build();
        
        entityManager.persist(movie);
        entityManager.flush();
        
        // When
        List<Movie> movies = movieRepository.findAllMoviesWithDirectors();
        
        // Then
        assertThat(movies).hasSize(1);
        assertThat(movies.get(0).getTitle()).isEqualTo("Test Movie");
        assertThat(movies.get(0).getDirector().getFirstName()).isEqualTo("Test");
        assertThat(movies.get(0).getDirector().getLastName()).isEqualTo("Director");
    }
    
    @Test
    public void shouldSaveMovie() {
        // Given
        Director director = Director.builder()
                .firstName("Test")
                .lastName("Director")
                .dateOfBirth(LocalDate.of(1980, 1, 1))
                .nationality("Test Nationality")
                .biography("Test Biography")
                .build();
        
        entityManager.persist(director);
        
        Movie movie = Movie.builder()
                .title("Test Movie")
                .releaseDate(LocalDate.of(2020, 1, 1))
                .description("Test Description")
                .genre("Action")
                .durationMinutes(120)
                .rating(8.5)
                .director(director)
                .build();
        
        // When
        Movie savedMovie = movieRepository.save(movie);
        
        // Then
        assertThat(savedMovie.getId()).isNotNull();
        assertThat(savedMovie.getTitle()).isEqualTo("Test Movie");
        assertThat(savedMovie.getDirector().getFirstName()).isEqualTo("Test");
    }
} 