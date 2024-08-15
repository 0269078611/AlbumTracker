package com.lucic.albumtracker.service;

import com.lucic.albumtracker.dto.GenreDTO;
import com.lucic.albumtracker.entity.GenreEntity;

import java.util.List;
import java.util.UUID;

public interface GenreService {

    List<GenreDTO> getAllGenres();

    GenreDTO getGenreById(UUID id);

    GenreEntity createGenre(GenreDTO genreDTO);

    GenreEntity updateGenre(GenreDTO genreDTO);

    void deleteGenre(UUID id);
}
