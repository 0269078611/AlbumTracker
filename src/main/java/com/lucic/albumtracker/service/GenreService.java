package com.lucic.albumtracker.service;


import com.lucic.albumtracker.entity.GenreEntity;

import java.util.List;
import java.util.UUID;

public interface GenreService {

    List<GenreEntity> getAllGenres();

    List<GenreEntity> searchGenres(String query);

    GenreEntity getGenreById(UUID id);

    GenreEntity createGenre(GenreEntity genreEntity);

    GenreEntity updateGenre(GenreEntity genreEntity);

    void deleteGenre(UUID id);
}
