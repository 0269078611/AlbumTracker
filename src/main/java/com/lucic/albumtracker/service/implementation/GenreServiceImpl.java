package com.lucic.albumtracker.service.implementation;

import com.lucic.albumtracker.dto.GenreDTO;
import com.lucic.albumtracker.entity.GenreEntity;
import com.lucic.albumtracker.mapper.GenreMapper;
import com.lucic.albumtracker.repository.GenreRepository;
import com.lucic.albumtracker.service.GenreService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class GenreServiceImpl implements GenreService {


    private final GenreRepository genreRepository;

    private final GenreMapper genreMapper;

    @Override
    public List<GenreDTO> getAllGenres() {

        List<GenreEntity> genres = genreRepository.findAll();
        return genreMapper.genreToGenreDTOList(genres);
    }

    @Override
    public GenreDTO getGenreById(UUID id) {
        GenreEntity genre = genreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Genre not found"));
        return genreMapper.genreToGenreDTO(genre);
    }


    @Override
    @Transactional
    public GenreEntity createGenre(GenreDTO genreDTO) {
        Optional<GenreEntity> existingGenre = genreRepository.findByName(genreDTO.getName());

        if (existingGenre.isPresent()) {
            throw new RuntimeException("Genre already exists");
        }

        GenreEntity genreEntity = genreMapper.genreDTOToGenre(genreDTO);
        return genreRepository.save(genreEntity);
    }

    @Override
    public GenreEntity updateGenre(GenreDTO genreDTO) {
        GenreEntity existingGenre = genreRepository.findById(genreDTO.getId())
                .orElseThrow(() -> new RuntimeException("Genre not found"));
        existingGenre.setName(genreDTO.getName());
        return genreRepository.save(existingGenre);
    }

    @Override
    public void deleteGenre(UUID id) {

        if (!genreRepository.existsById(id)) {
            throw new RuntimeException("Genre not found");
        }
        genreRepository.deleteById(id);
    }

}
