package com.movieapp.controller;

import com.movieapp.model.Director;
import com.movieapp.service.DirectorService;
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
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DirectorController.class)
public class DirectorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DirectorService directorService;

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
    public void shouldReturnDirectorsPage() throws Exception {
        // Given
        given(directorService.findAllDirectors()).willReturn(Arrays.asList(director1, director2));
        
        // When & Then
        mockMvc.perform(get("/directors"))
                .andExpect(status().isOk())
                .andExpect(view().name("directors/list"))
                .andExpect(model().attributeExists("directors"));
        
        verify(directorService).findAllDirectors();
    }
    
    @Test
    public void shouldReturnDirectorFormPage() throws Exception {
        // When & Then
        mockMvc.perform(get("/directors/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("directors/form"))
                .andExpect(model().attributeExists("director"));
    }
    
    @Test
    public void shouldReturnEditDirectorFormPage() throws Exception {
        // Given
        given(directorService.findDirectorById(1L)).willReturn(Optional.of(director1));
        
        // When & Then
        mockMvc.perform(get("/directors/1/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name("directors/form"))
                .andExpect(model().attributeExists("director"));
        
        verify(directorService).findDirectorById(1L);
    }
    
    @Test
    public void shouldCreateDirector() throws Exception {
        // Given
        given(directorService.saveDirector(any(Director.class))).willReturn(director1);
        
        // When & Then
        mockMvc.perform(post("/directors")
                .param("firstName", "Test1")
                .param("lastName", "Director1")
                .param("dateOfBirth", "1980-01-01")
                .param("nationality", "Test Nationality1")
                .param("biography", "Test Biography1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/directors"))
                .andExpect(flash().attributeExists("message"));
    }
    
    @Test
    public void shouldUpdateDirector() throws Exception {
        // Given
        given(directorService.saveDirector(any(Director.class))).willReturn(director1);
        
        // When & Then
        mockMvc.perform(post("/directors/1")
                .param("firstName", "Updated Test")
                .param("lastName", "Updated Director")
                .param("dateOfBirth", "1980-01-01")
                .param("nationality", "Updated Nationality")
                .param("biography", "Updated Biography"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/directors"))
                .andExpect(flash().attributeExists("message"));
    }
} 