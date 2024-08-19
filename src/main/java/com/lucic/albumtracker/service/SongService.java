package com.lucic.albumtracker.service;

import com.lucic.albumtracker.dto.AlbumDTO;
import com.lucic.albumtracker.dto.SongDTO;

import java.util.List;
import java.util.UUID;

public interface SongService {

        SongDTO getSongById(UUID songId);
        List<SongDTO> getSongsByAlbumId(UUID albumId);
        SongDTO createSong(UUID albumId, SongDTO songDTO);
        SongDTO updateSong(UUID songId, SongDTO songDTO);
        void deleteSong(UUID songId);
}

