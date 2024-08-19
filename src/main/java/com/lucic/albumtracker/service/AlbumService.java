package com.lucic.albumtracker.service;


import com.lucic.albumtracker.entity.AlbumEntity;
import com.lucic.albumtracker.entity.GenreEntity;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface AlbumService {

    Set<AlbumEntity> getAllAlbums();

    AlbumEntity getAlbumById(UUID id);

    AlbumEntity createAlbum(AlbumEntity albumEntity);

    AlbumEntity updateAlbum(AlbumEntity albumEntity);

    void deleteAlbum(UUID id);
}
