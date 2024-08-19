package com.lucic.albumtracker.controller;

import com.lucic.albumtracker.dto.AlbumDTO;
import com.lucic.albumtracker.service.AlbumService;
import com.lucic.albumtracker.service.ArtistService;
import com.lucic.albumtracker.service.GenreService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Set;
import java.util.UUID;
@Controller
@AllArgsConstructor
public class AlbumController {
    private final AlbumService albumService;

    private final GenreService genreService;
    private final ArtistService artistService;


    @GetMapping
    public String listAlbums(Model model) {
        Set<AlbumDTO> albums = albumService.getAllAlbums();
        model.addAttribute("albums", albums);
        return "artists/list";
    }


    @GetMapping("/{id}")
    public String getAlbumById(@PathVariable UUID id, Model model) {
        AlbumDTO album = albumService.getAlbumById(id);
        model.addAttribute("album", album);
        return "album/detail";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/album/add")
    public String showCreateForm(Model model, @RequestParam(required = false) String genreQuery) {
        model.addAttribute("album", new AlbumDTO());

        // Handle genre search
        if (genreQuery != null && !genreQuery.isEmpty()) {
            model.addAttribute("genres", genreService.searchGenres(genreQuery));
        } else {
            model.addAttribute("genres", Collections.emptyList());
        }

        model.addAttribute("artists", artistService.getAllArtists());

        return "/admin/albums/addAlbum";
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/admin/albums/add")
    public String createAlbum(@ModelAttribute AlbumDTO albumDTO) {
            AlbumDTO savedAlbum = albumService.createAlbum(albumDTO);
            return "redirect:/admin/albums/" + savedAlbum.getId() + "/songs";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable UUID id, Model model) {
        AlbumDTO album = albumService.getAlbumById(id);
        model.addAttribute("album", album);
        return "/admin/albums/editAlbum";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{id}/edit")
    public String updateAlbum(AlbumDTO albumDTO) {
        albumService.updateAlbum(albumDTO);
        return "redirect:/albums";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{id}/delete")
    public String deleteArtist(@PathVariable UUID id) {
        albumService.deleteAlbum(id);
        return "redirect:/albums";
    }

}
