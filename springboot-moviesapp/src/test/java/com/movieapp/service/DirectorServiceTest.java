package com.movieapp.service;

import com.movieapp.model.Director;
import com.movieapp.repository.DirectorRepository;
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
public class DirectorServiceTest {

    @Mock
    private DirectorRepository directorRepository;

    @InjectMocks
    private DirectorServiceImpl directorService;

    private Director director1;
    private Director director2;

    @BeforeEach
    void setUp() {
        director1 = Director.builder()
                .id(1L)
                .firstName("Test1")
                .lastName("Director1")
                .dateOfBirth(LocalDate.of(1980, 1, 1))
                .nationality("Test Nationality1")
                .biography("Test Biography1")
                .build();
        
        director2 = Director.builder()
                .id(2L)
                .firstName("Test2")
                .lastName("Director2")
                .dateOfBirth(LocalDate.of(1985, 1, 1))
                .nationality("Test Nationality2")
                .biography("Test Biography2")
                .build();
    }

    @Test
    public void shouldReturnAllDirectors() {
        // Given
        given(directorRepository.findAll()).willReturn(Arrays.asList(director1, director2));
        
        // When
        List<Director> directors = directorService.findAllDirectors();
        
        // Then
        assertThat(directors).hasSize(2);
        assertThat(directors.get(0).getFirstName()).isEqualTo("Test1");
        assertThat(directors.get(1).getFirstName()).isEqualTo("Test2");
        verify(directorRepository, times(1)).findAll();
    }
    
    @Test
    public void shouldReturnDirectorById() {
        // Given
        given(directorRepository.findById(1L)).willReturn(Optional.of(director1));
        
        // When
        Optional<Director> foundDirector = directorService.findDirectorById(1L);
        
        // Then
        assertThat(foundDirector).isPresent();
        assertThat(foundDirector.get().getFirstName()).isEqualTo("Test1");
        assertThat(foundDirector.get().getLastName()).isEqualTo("Director1");
        verify(directorRepository, times(1)).findById(1L);
    }
    
    @Test
    public void shouldReturnEmptyWhenDirectorNotFound() {
        // Given
        given(directorRepository.findById(99L)).willReturn(Optional.empty());
        
        // When
        Optional<Director> foundDirector = directorService.findDirectorById(99L);
        
        // Then
        assertThat(foundDirector).isEmpty();
        verify(directorRepository, times(1)).findById(99L);
    }
    
    @Test
    public void shouldSaveDirector() {
        // Given
        given(directorRepository.save(any(Director.class))).willReturn(director1);
        
        // When
        Director savedDirector = directorService.saveDirector(director1);
        
        // Then
        assertThat(savedDirector.getId()).isEqualTo(1L);
        assertThat(savedDirector.getFirstName()).isEqualTo("Test1");
        verify(directorRepository, times(1)).save(director1);
    }
    
    @Test
    public void shouldDeleteDirector() {
        // When
        directorService.deleteDirector(1L);
        
        // Then
        verify(directorRepository, times(1)).deleteById(1L);
    }
} 