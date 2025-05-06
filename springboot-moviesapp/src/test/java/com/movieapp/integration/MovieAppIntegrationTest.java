package com.movieapp.integration;

import com.movieapp.model.Director;
import com.movieapp.model.Movie;
import com.movieapp.repository.DirectorRepository;
import com.movieapp.repository.MovieRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class MovieAppIntegrationTest {

    @Autowired
    private DirectorRepository directorRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Test
    public void testDirectorMovieRelationship() {
        // Given
        Director director = Director.builder()
                .firstName("Integration")
                .lastName("Test")
                .dateOfBirth(LocalDate.of(1980, 1, 1))
                .nationality("Test Country")
                .biography("Integration test biography")
                .build();

        directorRepository.save(director);

        Movie movie1 = Movie.builder()
                .title("Integration Test Movie 1")
                .releaseDate(LocalDate.of(2020, 1, 1))
                .description("Integration test description 1")
                .genre("Test")
                .durationMinutes(100)
                .rating(8.0)
                .director(director)
                .build();

        Movie movie2 = Movie.builder()
                .title("Integration Test Movie 2")
                .releaseDate(LocalDate.of(2021, 1, 1))
                .description("Integration test description 2")
                .genre("Test")
                .durationMinutes(110)
                .rating(7.5)
                .director(director)
                .build();

        movieRepository.save(movie1);
        movieRepository.save(movie2);

        // When
        List<Movie> moviesWithDirectors = movieRepository.findAllMoviesWithDirectors();
        List<Director> allDirectors = directorRepository.findAll();

        // Then
        assertThat(moviesWithDirectors).hasSize(2);
        assertThat(moviesWithDirectors.get(0).getDirector().getFirstName()).isEqualTo("Integration");
        assertThat(moviesWithDirectors.get(1).getDirector().getFirstName()).isEqualTo("Integration");
        
        assertThat(allDirectors).hasSize(1);
        assertThat(allDirectors.get(0).getFirstName()).isEqualTo("Integration");
    }
} 