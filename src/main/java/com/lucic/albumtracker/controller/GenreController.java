package com.lucic.albumtracker.controller;

import com.lucic.albumtracker.dto.GenreDTO;
import com.lucic.albumtracker.entity.GenreEntity;
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
@RequestMapping("/genres")
@AllArgsConstructor
public class GenreController {

    private GenreService genreService;


    @GetMapping
    public String listGenres(Model model) {

        List<GenreDTO> genres = genreService.getAllGenres();
        model.addAttribute("genres", genres);
        return "genres/list";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/add")
    public String showCreateForm(Model model) {
        model.addAttribute("genre", new GenreDTO());
        return "genres/addGenre";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public String createGenre(@ModelAttribute("genre") GenreDTO genre) {
        genreService.createGenre(genre);
        return "redirect:/genres";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable UUID id, Model model) {
        GenreDTO genre = genreService.getGenreById(id);
        model.addAttribute("genre", genre);
        return "genres/edit";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{id}/edit")
    public String updateGenre(@ModelAttribute GenreDTO updatedGenre) {
        genreService.updateGenre(updatedGenre);
        return "redirect:/genres";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{id}/delete")
    public String deleteGenre(@PathVariable UUID id) {
        genreService.deleteGenre(id);
        return "redirect:/genres";
    }

}
