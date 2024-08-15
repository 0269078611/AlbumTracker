package com.lucic.albumtracker.controller;

import com.lucic.albumtracker.dto.ArtistDTO;
import com.lucic.albumtracker.service.ArtistService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.UUID;

@Controller
@AllArgsConstructor
@RequestMapping("/artists")
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

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/add")
    public String showCreateForm(Model model) {
        model.addAttribute("artist", new ArtistDTO());
        return "artists/addArtist";
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PostMapping
    public String createArtist(ArtistDTO artistDTO) {
        artistService.createArtist(artistDTO);
        return "redirect:/artists";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable UUID id, Model model) {
        ArtistDTO artist = artistService.getArtistById(id);
        model.addAttribute("artist", artist);
        return "artists/editArtist";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{id}/edit")
    public String updateArtist(ArtistDTO artistDTO) {
        artistService.updateArtist(artistDTO);
        return "redirect:/artists";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{id}/delete")
    public String deleteArtist(@PathVariable UUID id) {
        artistService.deleteArtist(id);
        return "redirect:/artists";
    }
}
