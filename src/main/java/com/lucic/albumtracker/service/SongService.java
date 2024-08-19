package com.lucic.albumtracker.service;


import com.lucic.albumtracker.entity.SongEntity;

import java.util.List;
import java.util.UUID;

public interface SongService {

        SongEntity getSongById(UUID songId);

        List<SongEntity> getSongsByAlbumId(UUID albumId);

        SongEntity createSong(UUID albumId, SongEntity songEntity);

        SongEntity updateSong(UUID songId, SongEntity songEntity);

        void deleteSong(UUID songId);
}

