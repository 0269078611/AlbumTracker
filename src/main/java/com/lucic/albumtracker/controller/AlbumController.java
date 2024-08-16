//package com.lucic.albumtracker.controller;
//
//import com.lucic.albumtracker.dto.AlbumDTO;
//import com.lucic.albumtracker.dto.ArtistDTO;
//import com.lucic.albumtracker.service.ArtistService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//
//import java.util.List;
//import java.util.UUID;
//
//public class AlbumController {
//    private AlbumService albumService;
//
//
//    @GetMapping
//    public String listAlbums(Model model) {
//        List<AlbumDTO> artists = artistService.getAllAlbums();
//        model.addAttribute("album", album);
//        return "artists/list";
//    }
//
//
//    @GetMapping("/{id}")
//    public String getAlbumById(@PathVariable UUID id, Model model) {
//        AlbumDTO album = albumService.getAlbumById(id);
//        model.addAttribute("album", album);
//        return "album/detail";
//    }
//
//    @PreAuthorize("hasRole('ADMIN')")
//    @GetMapping("/add")
//    public String showCreateForm(Model model) {
//        model.addAttribute("album", new AlbumDTO());
//        return "artists/addAlbum";
//    }
//
//    @PreAuthorize("hasRole('ADMIN')")
//    @PostMapping
//    public String createAlbum(AlbumDTO albumDTO) {
//        albumService.createAlbum(albumDTO);
//        return "redirect:/album";
//    }
//
//    @PreAuthorize("hasRole('ADMIN')")
//    @GetMapping("/{id}/edit")
//    public String showEditForm(@PathVariable UUID id, Model model) {
//        AlbumDTO album = albumService.getAlbumById(id);
//        model.addAttribute("album", album);
//        return "album/editAlbum";
//    }
//
//    @PreAuthorize("hasRole('ADMIN')")
//    @PostMapping("/{id}/edit")
//    public String updateAlbum(AlbumDTO albumDTO) {
//        albumService.updateAlbum(albumDTO);
//        return "redirect:/albums";
//    }
//
//    @PreAuthorize("hasRole('ADMIN')")
//    @PostMapping("/{id}/delete")
//    public String deleteArtist(@PathVariable UUID id) {
//        albumService.deleteAlbum(id);
//        return "redirect:/albums";
//    }
//
//
//}
