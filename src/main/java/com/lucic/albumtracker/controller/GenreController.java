package com.lucic.albumtracker.controller;

import com.lucic.albumtracker.dto.GenreDTO;
import com.lucic.albumtracker.entity.GenreEntity;
import com.lucic.albumtracker.exception.NotFoundException;
import com.lucic.albumtracker.service.GenreService;
import com.lucic.albumtracker.service.implementation.GenreServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@AllArgsConstructor
public class GenreController {

    private final GenreService genreService;

    @GetMapping("/genres")
    public String listGenres(Model model) {
        List<GenreDTO> genres = genreService.getAllGenres();
        model.addAttribute("genres", genres);
        model.addAttribute("genre", new GenreDTO());
        return "genres";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/admin/genres")
    public String manageGenres(Model model) {
        List<GenreDTO> genres = genreService.getAllGenres();
        model.addAttribute("genres", genres);
        model.addAttribute("genre", new GenreDTO());
        return "/admin/genres";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/admin/genres/add")
    public String createGenre(@ModelAttribute("genre") GenreDTO genre) {
        genreService.createGenre(genre);
        return "redirect:/admin/genres";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/admin/genres/edit/{id}")
    public String updateGenre(@PathVariable UUID id, @ModelAttribute("genre") GenreDTO updatedGenre) {
        updatedGenre.setId(id);
        genreService.updateGenre(updatedGenre);
        return "redirect:/admin/genres";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/admin/genres/delete/{id}")
    public String deleteGenre(@PathVariable UUID id,Model model) {
        System.out.println("deleteGenre called with ID: " + id); // Confirm the ID received
        try {
            genreService.deleteGenre(id);
        } catch (NotFoundException e) {
            model.addAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/admin/genres";
    }
}
