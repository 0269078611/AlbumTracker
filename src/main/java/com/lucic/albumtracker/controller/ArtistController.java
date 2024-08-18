package com.lucic.albumtracker.controller;

import com.lucic.albumtracker.dto.ArtistDTO;
import com.lucic.albumtracker.dto.GenreDTO;
import com.lucic.albumtracker.exception.NotFoundException;
import com.lucic.albumtracker.service.ArtistService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@AllArgsConstructor
public class ArtistController {

    private ArtistService artistService;


    @GetMapping
    public String listArtists(Model model) {
        List<ArtistDTO> artists = artistService.getAllArtists();
        model.addAttribute("artists", artists);
        return "artists/list";
    }


    @GetMapping("/{id}")
    public String getArtistById(@PathVariable UUID id, Model model) {
        ArtistDTO artist = artistService.getArtistById(id);
        model.addAttribute("artist", artist);
        return "artists/detail";
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/admin/artists")
    public String manageGenres(Model model) {
        List<ArtistDTO> artists = artistService.getAllArtists();
        model.addAttribute("artists", artists);
        model.addAttribute("artist", new ArtistDTO());
        return "/admin/artists";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/admin/artists/add")
    public String createArtist(@ModelAttribute("artist") ArtistDTO artistDTO) {
        artistService.createArtist(artistDTO);
        return "redirect:/admin/artists";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/admin/artists/edit/{id}")
    public String updateArtist(ArtistDTO artistDTO) {
        artistService.updateArtist(artistDTO);
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
