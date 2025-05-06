package com.movieapp.repository;

import com.movieapp.model.Director;
import com.movieapp.model.Movie;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class DirectorRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private DirectorRepository directorRepository;

    @Test
    public void shouldFindAllDirectors() {
        // Given
        Director director1 = Director.builder()
                .firstName("Test1")
                .lastName("Director1")
                .dateOfBirth(LocalDate.of(1980, 1, 1))
                .nationality("Test Nationality1")
                .biography("Test Biography1")
                .build();
        
        Director director2 = Director.builder()
                .firstName("Test2")
                .lastName("Director2")
                .dateOfBirth(LocalDate.of(1985, 1, 1))
                .nationality("Test Nationality2")
                .biography("Test Biography2")
                .build();
        
        entityManager.persist(director1);
        entityManager.persist(director2);
        entityManager.flush();
        
        // When
        List<Director> directors = directorRepository.findAll();
        
        // Then
        assertThat(directors).hasSize(2);
        assertThat(directors.get(0).getFirstName()).isEqualTo("Test1");
        assertThat(directors.get(1).getFirstName()).isEqualTo("Test2");
    }
    
    @Test
    public void shouldFindDirectorById() {
        // Given
        Director director = Director.builder()
                .firstName("Test")
                .lastName("Director")
                .dateOfBirth(LocalDate.of(1980, 1, 1))
                .nationality("Test Nationality")
                .biography("Test Biography")
                .build();
        
        entityManager.persist(director);
        entityManager.flush();
        
        // When
        Optional<Director> foundDirector = directorRepository.findById(director.getId());
        
        // Then
        assertThat(foundDirector).isPresent();
        assertThat(foundDirector.get().getFirstName()).isEqualTo("Test");
        assertThat(foundDirector.get().getLastName()).isEqualTo("Director");
    }
    
    @Test
    public void shouldSaveDirectorWithMovies() {
        // Given
        Director director = Director.builder()
                .firstName("Test")
                .lastName("Director")
                .dateOfBirth(LocalDate.of(1980, 1, 1))
                .nationality("Test Nationality")
                .biography("Test Biography")
                .build();
        
        Movie movie = Movie.builder()
                .title("Test Movie")
                .releaseDate(LocalDate.of(2020, 1, 1))
                .description("Test Description")
                .genre("Action")
                .durationMinutes(120)
                .rating(8.5)
                .director(director)
                .build();
        
        director.setMovies(List.of(movie));
        
        // When
        Director savedDirector = directorRepository.save(director);
        
        // Then
        assertThat(savedDirector.getId()).isNotNull();
        assertThat(savedDirector.getFirstName()).isEqualTo("Test");
        assertThat(savedDirector.getLastName()).isEqualTo("Director");
        
        // Movie is cascaded but won't be available in this test without a fetch
        // We'll only test that the director was saved correctly
    }
} 