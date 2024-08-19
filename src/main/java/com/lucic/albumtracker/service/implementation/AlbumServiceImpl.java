package com.lucic.albumtracker.service.implementation;



import com.lucic.albumtracker.entity.AlbumEntity;
import com.lucic.albumtracker.entity.ArtistEntity;
import com.lucic.albumtracker.entity.GenreEntity;
import com.lucic.albumtracker.entity.SongEntity;
import com.lucic.albumtracker.exception.NotFoundException;

import com.lucic.albumtracker.repository.AlbumRepository;
import com.lucic.albumtracker.repository.ArtistRepository;
import com.lucic.albumtracker.repository.GenreRepository;
import com.lucic.albumtracker.repository.SongRepository;
import com.lucic.albumtracker.service.AlbumService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AlbumServiceImpl implements AlbumService {

    private final AlbumRepository albumRepository;
    private final SongRepository songRepository;
    private final GenreRepository genreRepository;
    private final ArtistRepository artistRepository;

    @Override
    public Set<AlbumEntity> getAllAlbums() {
        return albumRepository.findAll().stream()
                .collect(Collectors.toSet());
    }

    @Override
    public AlbumEntity getAlbumById(UUID albumId) {
        return albumRepository.findById(albumId)
                .orElseThrow(() -> new NotFoundException("Album not found"));
    }

    @Override
    @Transactional
    public AlbumEntity createAlbum(AlbumEntity albumEntity) {
        GenreEntity genre = genreRepository.findById(albumEntity.getGenre().getId())
                .orElseThrow(() -> new NotFoundException("Genre not found"));

        Set<ArtistEntity> artists = new HashSet<>(artistRepository.findAllById(albumEntity.getArtists().stream()
                .map(ArtistEntity::getId)
                .collect(Collectors.toSet())));
        if (artists.size() != albumEntity.getArtists().size()) {
            throw new NotFoundException("One or more artists not found");
        }

        albumEntity.setGenre(genre);
        albumEntity.setArtists(artists);

        return albumRepository.save(albumEntity);
    }

    @Override
    @Transactional
    public AlbumEntity updateAlbum(AlbumEntity albumEntity) {
        AlbumEntity existingAlbum = albumRepository.findById(albumEntity.getId())
                .orElseThrow(() -> new NotFoundException("Album not found"));

        existingAlbum.setTitle(albumEntity.getTitle());
        existingAlbum.setReleaseDate(albumEntity.getReleaseDate());

        if (albumEntity.getGenre() != null) {
            GenreEntity genreEntity = genreRepository.findById(albumEntity.getGenre().getId())
                    .orElseThrow(() -> new NotFoundException("Genre not found"));
            existingAlbum.setGenre(genreEntity);
        }

        if (albumEntity.getArtists() != null && !albumEntity.getArtists().isEmpty()) {
            Set<ArtistEntity> artists = new HashSet<>(artistRepository.findAllById(albumEntity.getArtists().stream()
                    .map(ArtistEntity::getId)
                    .collect(Collectors.toSet())));
            if (artists.size() != albumEntity.getArtists().size()) {
                throw new NotFoundException("One or more artists not found");
            }
            existingAlbum.setArtists(artists);
        }

        if (albumEntity.getSongs() != null && !albumEntity.getSongs().isEmpty()) {
            Set<SongEntity> songs = new HashSet<>(songRepository.findAllById(albumEntity.getSongs().stream()
                    .map(SongEntity::getId)
                    .collect(Collectors.toSet())));
            if (songs.size() != albumEntity.getSongs().size()) {
                throw new NotFoundException("One or more songs not found");
            }
            existingAlbum.setSongs(songs);
        }

        return albumRepository.save(existingAlbum);
    }

    @Override
    public void deleteAlbum(UUID id) {
        AlbumEntity album = albumRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Album not found"));

        albumRepository.delete(album);
    }
}
