package com.lucic.albumtracker.service.implementation;


import com.lucic.albumtracker.entity.AlbumEntity;
import com.lucic.albumtracker.entity.ArtistEntity;
import com.lucic.albumtracker.entity.SongEntity;
import com.lucic.albumtracker.exception.NotFoundException;

import com.lucic.albumtracker.repository.AlbumRepository;
import com.lucic.albumtracker.repository.ArtistRepository;
import com.lucic.albumtracker.repository.SongRepository;
import com.lucic.albumtracker.service.SongService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class SongServiceImpl implements SongService {

    private final AlbumRepository albumRepository;
    private final SongRepository songRepository;
    private final ArtistRepository artistRepository;

    @Override
    public List<SongEntity> getSongsByAlbumId(UUID albumId) {
        return songRepository.findByAlbumId(albumId);
    }

    @Override
    public SongEntity getSongById(UUID songId) {
        return songRepository.findById(songId)
                .orElseThrow(() -> new NotFoundException("Song not found"));
    }

    @Override
    public SongEntity createSong(UUID albumId, SongEntity songEntity) {
        AlbumEntity album = albumRepository.findById(albumId)
                .orElseThrow(() -> new NotFoundException("Album not found"));

        Set<UUID> artistIds = songEntity.getArtists().stream()
                .map(ArtistEntity::getId)
                .collect(Collectors.toSet());
        Set<ArtistEntity> artists = new HashSet<>(artistRepository.findAllById(artistIds));

        songEntity.setAlbum(album);
        songEntity.setArtists(artists);

        return songRepository.save(songEntity);
    }

    @Override
    public SongEntity updateSong(UUID songId, SongEntity songEntity) {
        SongEntity existingSong = songRepository.findById(songId)
                .orElseThrow(() -> new NotFoundException("Song not found"));

        Set<UUID> artistIds = songEntity.getArtists().stream()
                .map(ArtistEntity::getId)
                .collect(Collectors.toSet());
        Set<ArtistEntity> artists = new HashSet<>(artistRepository.findAllById(artistIds));

        existingSong.setTitle(songEntity.getTitle());
        existingSong.setTrackNumber(songEntity.getTrackNumber());
        existingSong.setDuration(songEntity.getDuration());
        existingSong.setArtists(artists);

        if (songEntity.getAlbum() != null) {
            AlbumEntity album = albumRepository.findById(songEntity.getAlbum().getId())
                    .orElseThrow(() -> new NotFoundException("Album not found"));
            existingSong.setAlbum(album);
        }

        return songRepository.save(existingSong);
    }

    @Override
    public void deleteSong(UUID songId) {
        SongEntity song = songRepository.findById(songId)
                .orElseThrow(() -> new NotFoundException("Song not found"));
        songRepository.delete(song);
    }
}

