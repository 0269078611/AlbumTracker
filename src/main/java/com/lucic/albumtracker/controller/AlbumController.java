package com.lucic.albumtracker.controller;

import com.lucic.albumtracker.entity.AlbumEntity;
import com.lucic.albumtracker.service.AlbumService;
import com.lucic.albumtracker.service.ArtistService;
import com.lucic.albumtracker.service.GenreService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;
@Controller
@AllArgsConstructor
public class AlbumController {
    private final AlbumService albumService;
    private final GenreService genreService;
    private final ArtistService artistService;
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("admin/albums")
    public String listAlbums(Model model) {
        Set<AlbumEntity> albums = albumService.getAllAlbums();
        model.addAttribute("albums", albums);
        return "admin/albums/list";
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("admin/albums/{id}")
    public String getAlbumById(@PathVariable UUID id, Model model) {
        AlbumEntity album = albumService.getAlbumById(id);
        model.addAttribute("album", album);
        return "admin/albums/details";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/admin/albums/add")
    public String createAlbumForm(Model model) {
        model.addAttribute("album", new AlbumEntity());

        model.addAttribute("genres", genreService.getAllGenres());
        model.addAttribute("artists", artistService.getAllArtists());

        return "admin/albums/addAlbum";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/admin/albums/add")
    public String createAlbum(@ModelAttribute AlbumEntity albumEntity) {
        AlbumEntity savedAlbum = albumService.createAlbum(albumEntity);
        return "redirect:/admin/albums/" + savedAlbum.getId() + "/songs";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/admin/albums/edit/{albumId}")
    public String updateAlbumForm(@PathVariable UUID albumId, Model model) {
        AlbumEntity album = albumService.getAlbumById(albumId);
        model.addAttribute("album", album);
        return "/admin/albums/editAlbum";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/admin/albums/edit/{albumId}")
    public String updateAlbum(@PathVariable UUID albumId, @ModelAttribute AlbumEntity albumEntity) {
        albumEntity.setId(albumId);
        albumService.updateAlbum(albumEntity);
        return "redirect:admin/albums";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("admin/albums/delete/{albumId}")
    public String deleteAlbum(@PathVariable UUID albumId) {
        albumService.deleteAlbum(albumId);
        return "redirect:admin/albums";
    }
}
