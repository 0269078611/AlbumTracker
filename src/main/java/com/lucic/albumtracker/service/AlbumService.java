package com.lucic.albumtracker.service;

import com.lucic.albumtracker.dto.AlbumDTO;
import com.lucic.albumtracker.dto.GenreDTO;
import com.lucic.albumtracker.entity.AlbumEntity;
import com.lucic.albumtracker.entity.GenreEntity;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface AlbumService {

    Set<AlbumDTO> getAllAlbums();

    AlbumDTO getAlbumById(UUID id);

    AlbumDTO createAlbum(AlbumDTO albumDTO);

    AlbumDTO updateAlbum(AlbumDTO albumDTO);

    void deleteAlbum(UUID id);


}
