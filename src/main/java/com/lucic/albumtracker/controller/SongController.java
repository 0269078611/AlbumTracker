package com.lucic.albumtracker.controller;


import com.lucic.albumtracker.entity.AlbumEntity;
import com.lucic.albumtracker.entity.SongEntity;
import com.lucic.albumtracker.exception.NotFoundException;
import com.lucic.albumtracker.service.AlbumService;
import com.lucic.albumtracker.service.ArtistService;
import com.lucic.albumtracker.service.SongService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@AllArgsConstructor
public class SongController {

    private final SongService songService;
    private final AlbumService albumService;
    private final ArtistService artistService;

    @GetMapping("songs/{songId}")
    public String getSongDetails(@PathVariable UUID songId, Model model) {
        SongEntity song = songService.getSongById(songId);
        model.addAttribute("song", song);
        return "songs/details";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/admin/albums/{albumId}/songs")
    public String manageSongs(@PathVariable UUID albumId, Model model) {
        AlbumEntity album = albumService.getAlbumById(albumId);
        List<SongEntity> songs = songService.getSongsByAlbumId(albumId);
        model.addAttribute("album", album);
        model.addAttribute("songs", songs);
        model.addAttribute("albumId", albumId);
        model.addAttribute("artists",artistService.getAllArtists());
        model.addAttribute("song", new SongEntity());
        return "admin/albums/songs/manageSongs";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/admin/albums/{albumId}/songs/add")
    public String createSong(@PathVariable UUID albumId, @ModelAttribute("song") SongEntity songEntity) {
        songService.createSong(albumId, songEntity);
        return "redirect:/admin/albums/" + albumId + "/songs";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/admin/albums/{albumId}/songs/edit/{songId}")
    public String updateSong(@PathVariable UUID albumId, @PathVariable UUID songId, @ModelAttribute("song") SongEntity songEntity) {
        songEntity.setId(songId);
        songService.updateSong(songId, songEntity);
        return "redirect:/admin/albums/" + albumId + "/songs";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/admin/albums/{albumId}/songs/delete/{songId}")
    public String deleteSong(@PathVariable UUID albumId, @PathVariable UUID songId, Model model) {
        try {
            songService.deleteSong(songId);
        } catch (NotFoundException e) {
            model.addAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/admin/albums/" + albumId + "/songs";
    }
}
