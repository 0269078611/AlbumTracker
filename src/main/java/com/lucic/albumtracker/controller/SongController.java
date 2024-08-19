package com.lucic.albumtracker.controller;


import com.lucic.albumtracker.entity.SongEntity;
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

    @GetMapping("/{songId}")
    public String getSongDetails(@PathVariable UUID songId, Model model) {
        SongEntity song = songService.getSongById(songId);
        model.addAttribute("song", song);
        return "songs/song-detail";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/admin/albums/{albumId}/songs")
    public String manageSongs(@PathVariable UUID albumId, Model model) {
        List<SongEntity> songs = songService.getSongsByAlbumId(albumId);
        model.addAttribute("songs", songs);
        model.addAttribute("albumId", albumId);
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
    @PostMapping("/admin/albums/{albumId}/songs/{songId}/edit")
    public String updateSong(@PathVariable UUID albumId, @PathVariable UUID songId, @ModelAttribute("song") SongEntity songEntity) {
        songEntity.setId(songId);
        songService.updateSong(songId, songEntity);
        return "redirect:/admin/albums/" + albumId + "/songs";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/admin/albums/{albumId}/songs/{songId}/delete")
    public String deleteSong(@PathVariable UUID albumId, @PathVariable UUID songId) {
        songService.deleteSong(songId);
        return "redirect:/admin/albums/" + albumId + "/songs";
    }
}
