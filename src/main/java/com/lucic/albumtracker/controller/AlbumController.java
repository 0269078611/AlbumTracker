package com.lucic.albumtracker.controller;


import com.lucic.albumtracker.entity.AlbumEntity;
import com.lucic.albumtracker.entity.GenreEntity;
import com.lucic.albumtracker.service.AlbumService;
import com.lucic.albumtracker.service.ArtistService;
import com.lucic.albumtracker.service.GenreService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
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
        Set<AlbumEntity> albums = albumService.getAllAlbums();
        model.addAttribute("albums", albums);
        return "albums/list";
    }

    @GetMapping("/{id}")
    public String getAlbumById(@PathVariable UUID id, Model model) {
        AlbumEntity album = albumService.getAlbumById(id);
        model.addAttribute("album", album);
        return "album/detail";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/albums/add")
    public String showCreateForm(Model model, @RequestParam(required = false) String genreQuery) {
        model.addAttribute("album", new AlbumEntity());


        model.addAttribute("genres", genreService.getAllGenres());


        model.addAttribute("artists", artistService.getAllArtists());

        return "/admin/albums/addAlbum";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/admin/albums/add")
    public String createAlbum(@ModelAttribute AlbumEntity albumEntity) {
        AlbumEntity savedAlbum = albumService.createAlbum(albumEntity);
        return "redirect:/admin/albums/" + savedAlbum.getId() + "/songs";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable UUID id, Model model) {
        AlbumEntity album = albumService.getAlbumById(id);
        model.addAttribute("album", album);
        return "/admin/albums/editAlbum";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{id}/edit")
    public String updateAlbum(@PathVariable UUID id, @ModelAttribute AlbumEntity albumEntity) {
        albumEntity.setId(id); // Ensure the ID is set for the update
        albumService.updateAlbum(albumEntity);
        return "redirect:/albums";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{id}/delete")
    public String deleteAlbum(@PathVariable UUID id) {
        albumService.deleteAlbum(id);
        return "redirect:/albums";
    }
}
