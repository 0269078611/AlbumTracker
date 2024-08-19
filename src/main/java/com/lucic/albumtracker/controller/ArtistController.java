package com.lucic.albumtracker.controller;


import com.lucic.albumtracker.entity.ArtistEntity;
import com.lucic.albumtracker.exception.NotFoundException;
import com.lucic.albumtracker.service.ArtistService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Controller
@AllArgsConstructor
public class ArtistController {

    private final ArtistService artistService;

    @GetMapping("/artists")
    public String listArtists(Model model) {
        Set<ArtistEntity> artists = artistService.getAllArtists();
        model.addAttribute("artists", artists);
        return "artists/list";
    }

    @GetMapping("/artists/{id}")
    public String getArtistById(@PathVariable UUID id, Model model) {
        ArtistEntity artist = artistService.getArtistById(id);
        model.addAttribute("artist", artist);
        return "artists/detail";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/admin/artists")
    public String manageArtists(Model model) {
        Set<ArtistEntity> artists = artistService.getAllArtists();
        model.addAttribute("artists", artists);
        model.addAttribute("artist", new ArtistEntity()); // Prepare a new entity for the form
        return "/admin/artists";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/admin/artists/add")
    public String createArtist(@ModelAttribute("artist") ArtistEntity artist) {
        artistService.createArtist(artist);
        return "redirect:/admin/artists";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/admin/artists/edit/{id}")
    public String updateArtist(@PathVariable UUID id, @ModelAttribute("artist") ArtistEntity updatedArtist) {
        updatedArtist.setId(id); // Ensure the ID is set for the update
        artistService.updateArtist(updatedArtist);
        return "redirect:/admin/artists";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/admin/artists/delete/{id}")
    public String deleteArtist(@PathVariable UUID id, Model model) {
        try {
            artistService.deleteArtist(id);
        } catch (NotFoundException e) {
            model.addAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/admin/artists";
    }
}
