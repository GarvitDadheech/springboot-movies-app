package com.movieapp.controller;

import com.movieapp.model.Director;
import com.movieapp.model.Movie;
import com.movieapp.service.DirectorService;
import com.movieapp.service.MovieService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MovieController.class)
public class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieService movieService;

    @MockBean
    private DirectorService directorService;

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
    public void shouldReturnMoviesPage() throws Exception {
        // Given
        given(movieService.findAllMoviesWithDirectors()).willReturn(Arrays.asList(movie1, movie2));
        
        // When & Then
        mockMvc.perform(get("/movies"))
                .andExpect(status().isOk())
                .andExpect(view().name("movies/list"))
                .andExpect(model().attributeExists("movies"));
        
        verify(movieService).findAllMoviesWithDirectors();
    }
    
    @Test
    public void shouldReturnMovieFormPage() throws Exception {
        // Given
        given(directorService.findAllDirectors()).willReturn(Arrays.asList(director));
        
        // When & Then
        mockMvc.perform(get("/movies/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("movies/form"))
                .andExpect(model().attributeExists("movie"))
                .andExpect(model().attributeExists("directors"));
        
        verify(directorService).findAllDirectors();
    }
    
    @Test
    public void shouldReturnEditMovieFormPage() throws Exception {
        // Given
        given(movieService.findMovieById(1L)).willReturn(Optional.of(movie1));
        given(directorService.findAllDirectors()).willReturn(Arrays.asList(director));
        
        // When & Then
        mockMvc.perform(get("/movies/1/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name("movies/form"))
                .andExpect(model().attributeExists("movie"))
                .andExpect(model().attributeExists("directors"));
        
        verify(movieService).findMovieById(1L);
        verify(directorService).findAllDirectors();
    }
    
    @Test
    public void shouldCreateMovie() throws Exception {
        // Given
        given(movieService.saveMovie(any(Movie.class))).willReturn(movie1);
        given(directorService.findDirectorById(anyLong())).willReturn(Optional.of(director));
        
        // When & Then
        mockMvc.perform(post("/movies")
                .param("title", "Test Movie 1")
                .param("releaseDate", "2020-01-01")
                .param("description", "Test Description 1")
                .param("genre", "Action")
                .param("durationMinutes", "120")
                .param("rating", "8.5")
                .param("director", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/movies"))
                .andExpect(flash().attributeExists("message"));
    }
    
    @Test
    public void shouldUpdateMovie() throws Exception {
        // Given
        given(movieService.saveMovie(any(Movie.class))).willReturn(movie1);
        given(directorService.findDirectorById(anyLong())).willReturn(Optional.of(director));
        
        // When & Then
        mockMvc.perform(post("/movies/1")
                .param("title", "Updated Test Movie")
                .param("releaseDate", "2020-01-01")
                .param("description", "Updated Test Description")
                .param("genre", "Action")
                .param("durationMinutes", "120")
                .param("rating", "8.5")
                .param("director", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/movies"))
                .andExpect(flash().attributeExists("message"));
    }
} 