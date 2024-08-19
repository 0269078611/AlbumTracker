package com.lucic.albumtracker.service.implementation;


import com.lucic.albumtracker.dto.AlbumDTO;
import com.lucic.albumtracker.entity.AlbumEntity;
import com.lucic.albumtracker.entity.ArtistEntity;
import com.lucic.albumtracker.entity.GenreEntity;
import com.lucic.albumtracker.entity.SongEntity;
import com.lucic.albumtracker.exception.NotFoundException;
import com.lucic.albumtracker.mapper.AlbumMapper;
import com.lucic.albumtracker.mapper.ArtistMapper;
import com.lucic.albumtracker.mapper.GenreMapper;
import com.lucic.albumtracker.repository.AlbumRepository;
import com.lucic.albumtracker.repository.ArtistRepository;
import com.lucic.albumtracker.repository.GenreRepository;
import com.lucic.albumtracker.repository.SongRepository;
import com.lucic.albumtracker.service.AlbumService;
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
    private final AlbumMapper albumMapper;
    
    @Override
    public Set<AlbumDTO> getAllAlbums() {
        return albumRepository.findAll().stream()
                .map(albumMapper::toDto)
                .collect(Collectors.toSet());
    }

    @Override
    public AlbumDTO getAlbumById(UUID albumId) {
        AlbumEntity album = albumRepository.findById(albumId)
                .orElseThrow(() -> new NotFoundException("Album not found"));
        return albumMapper.toDto(album);
    }
    @Override
    public AlbumDTO createAlbum(AlbumDTO albumDTO) {
        GenreEntity genre = genreRepository.findById(albumDTO.getGenreId())
                .orElseThrow(() -> new NotFoundException("Genre not found"));

        Set<ArtistEntity> artists = new HashSet<>(artistRepository.findAllById(albumDTO.getArtistIds()));
        if (artists.size() != albumDTO.getArtistIds().size()) {
            throw new NotFoundException("Artist not found");
        }

        AlbumEntity albumEntity = albumMapper.toEntity(albumDTO);
        albumEntity.setGenre(genre);
        albumEntity.setArtists(artists);


        AlbumEntity savedAlbum = albumRepository.save(albumEntity);

        return albumMapper.toDto(savedAlbum);
    }
    @Override
    public AlbumDTO updateAlbum(AlbumDTO albumDTO) {
        AlbumEntity existingAlbum = albumRepository.findById(albumDTO.getId())
                .orElseThrow(() -> new NotFoundException("Album not found "));

        existingAlbum.setTitle(albumDTO.getTitle());
        existingAlbum.setReleaseDate(albumDTO.getReleaseDate());

        if (albumDTO.getGenreId() != null) {
            GenreEntity genreEntity = genreRepository.findById(albumDTO.getGenreId())
                    .orElseThrow(() -> new NotFoundException("Genre not found."));
            existingAlbum.setGenre(genreEntity);
        }
        if (albumDTO.getArtistIds() != null && !albumDTO.getArtistIds().isEmpty()) {
            Set<ArtistEntity> artists = albumMapper.albumMapIdsToArtists(albumDTO.getArtistIds(), artistRepository);
            existingAlbum.setArtists(artists);
        }

        if (albumDTO.getSongIds() != null && !albumDTO.getSongIds().isEmpty()) {
            Set<SongEntity> songs = albumMapper.albumMapIdsToSongs(albumDTO.getSongIds(), songRepository);
            existingAlbum.setSongs(songs);
        }
        existingAlbum = albumRepository.save(existingAlbum);
        return albumMapper.toDto(existingAlbum);
    }

    @Override
    public void deleteAlbum(UUID id) {
        AlbumEntity album = albumRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Album not found"));

        albumRepository.delete(album);
    }
}
