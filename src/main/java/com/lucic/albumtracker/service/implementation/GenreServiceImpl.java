package com.lucic.albumtracker.service.implementation;


import com.lucic.albumtracker.entity.AlbumEntity;
import com.lucic.albumtracker.entity.GenreEntity;
import com.lucic.albumtracker.exception.NotFoundException;

import com.lucic.albumtracker.repository.AlbumRepository;
import com.lucic.albumtracker.repository.GenreRepository;
import com.lucic.albumtracker.service.GenreService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;
    private final AlbumRepository albumRepository;

    @Override
    public List<GenreEntity> getAllGenres() {
        return genreRepository.findAll();
    }

    @Override
    public List<GenreEntity> searchGenres(String query) {
        return genreRepository.findByNameContainingIgnoreCase(query);
    }

    @Override
    public GenreEntity getGenreById(UUID id) {
        return genreRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Genre not found"));
    }

    @Override
    @Transactional
    public GenreEntity createGenre(GenreEntity genreEntity) {
        Optional<GenreEntity> existingGenre = genreRepository.findByName(genreEntity.getName());

        if (existingGenre.isPresent()) {
            throw new RuntimeException("Genre already exists");
        }

        return genreRepository.save(genreEntity);
    }

    @Override
    public GenreEntity updateGenre(GenreEntity genreEntity) {
        GenreEntity existingGenre = genreRepository.findById(genreEntity.getId())
                .orElseThrow(() -> new NotFoundException("Genre not found"));

        existingGenre.setName(genreEntity.getName());
        return genreRepository.save(existingGenre);
    }

    @Override
    public void deleteGenre(UUID id) {
        GenreEntity genre = genreRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Genre not found"));

        for (AlbumEntity album : genre.getAlbums()) {
            album.setGenre(null);
            albumRepository.save(album);
        }

        genreRepository.delete(genre);
    }
}
