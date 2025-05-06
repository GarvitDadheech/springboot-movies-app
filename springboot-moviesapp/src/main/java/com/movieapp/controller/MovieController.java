package com.movieapp.controller;

import com.movieapp.model.Movie;
import com.movieapp.service.DirectorService;
import com.movieapp.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;
    private final DirectorService directorService;

    @GetMapping
    public String getAllMovies(Model model) {
        model.addAttribute("movies", movieService.findAllMoviesWithDirectors());
        return "movies/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("movie", new Movie());
        model.addAttribute("directors", directorService.findAllDirectors());
        return "movies/form";
    }

    @PostMapping
    public String createMovie(@Valid @ModelAttribute Movie movie,
                              BindingResult result,
                              Model model,
                              RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("directors", directorService.findAllDirectors());
            return "movies/form";
        }
        
        movieService.saveMovie(movie);
        redirectAttributes.addFlashAttribute("message", "Movie added successfully!");
        return "redirect:/movies";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Movie movie = movieService.findMovieById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid movie Id:" + id));
        model.addAttribute("movie", movie);
        model.addAttribute("directors", directorService.findAllDirectors());
        return "movies/form";
    }

    @PostMapping("/{id}")
    public String updateMovie(@PathVariable Long id,
                             @Valid @ModelAttribute Movie movie,
                             BindingResult result,
                             Model model,
                             RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            movie.setId(id);
            model.addAttribute("directors", directorService.findAllDirectors());
            return "movies/form";
        }
        
        movieService.saveMovie(movie);
        redirectAttributes.addFlashAttribute("message", "Movie updated successfully!");
        return "redirect:/movies";
    }
} 