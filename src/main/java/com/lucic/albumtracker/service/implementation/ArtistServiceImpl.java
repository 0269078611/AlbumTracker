package com.lucic.albumtracker.service.implementation;


import com.lucic.albumtracker.entity.ArtistEntity;
import com.lucic.albumtracker.exception.NotFoundException;

import com.lucic.albumtracker.repository.ArtistRepository;
import com.lucic.albumtracker.service.ArtistService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ArtistServiceImpl implements ArtistService {

  private final ArtistRepository artistRepository;

  @Override
  public Set<ArtistEntity> getAllArtists() {
    return artistRepository.findAll().stream()
            .collect(Collectors.toSet());
  }

  @Override
  public ArtistEntity getArtistById(UUID id) {
    return artistRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Artist not found"));
  }

  @Override
  @Transactional
  public ArtistEntity createArtist(ArtistEntity artistEntity) {
    Optional<ArtistEntity> existingArtist = artistRepository.findByName(artistEntity.getName());

    if (existingArtist.isPresent()) {
      throw new RuntimeException("Artist already exists.");
    }

    return artistRepository.save(artistEntity);
  }

  @Override
  @Transactional
  public ArtistEntity updateArtist(ArtistEntity artistEntity) {
    ArtistEntity existingArtist = artistRepository.findById(artistEntity.getId())
            .orElseThrow(() -> new NotFoundException("Artist not found"));

    existingArtist.setName(artistEntity.getName());

    return artistRepository.save(existingArtist);
  }

  @Override
  public void deleteArtist(UUID id) {
    if (!artistRepository.existsById(id)) {
      throw new NotFoundException("Artist not found");
    }

    artistRepository.deleteById(id);
  }
}