package com.movieapp.controller;

import com.movieapp.model.Director;
import com.movieapp.service.DirectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/directors")
@RequiredArgsConstructor
public class DirectorController {

    private final DirectorService directorService;

    @GetMapping
    public String getAllDirectors(Model model) {
        model.addAttribute("directors", directorService.findAllDirectors());
        return "directors/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("director", new Director());
        return "directors/form";
    }

    @PostMapping
    public String createDirector(@Valid @ModelAttribute Director director, 
                                 BindingResult result, 
                                 RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "directors/form";
        }
        
        directorService.saveDirector(director);
        redirectAttributes.addFlashAttribute("message", "Director added successfully!");
        return "redirect:/directors";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Director director = directorService.findDirectorById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid director Id:" + id));
        model.addAttribute("director", director);
        return "directors/form";
    }

    @PostMapping("/{id}")
    public String updateDirector(@PathVariable Long id, 
                                @Valid @ModelAttribute Director director,
                                BindingResult result,
                                RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            director.setId(id);
            return "directors/form";
        }
        
        directorService.saveDirector(director);
        redirectAttributes.addFlashAttribute("message", "Director updated successfully!");
        return "redirect:/directors";
    }
} 