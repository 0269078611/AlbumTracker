package com.lucic.albumtracker.service.implementation;

import com.lucic.albumtracker.dto.ArtistDTO;
import com.lucic.albumtracker.entity.ArtistEntity;
import com.lucic.albumtracker.exception.NotFoundException;
import com.lucic.albumtracker.mapper.ArtistMapper;
import com.lucic.albumtracker.repository.ArtistRepository;
import com.lucic.albumtracker.service.ArtistService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ArtistServiceImpl implements ArtistService {

  private final ArtistRepository artistRepository;

  private final ArtistMapper artistMapper;



  public List<ArtistDTO> getAllArtists() {
    return artistRepository.findAll().stream()
            .map(artistMapper::artistToArtistDTO)
            .collect(Collectors.toList());
  }


  public ArtistDTO getArtistById(UUID id) {
    ArtistEntity artist = artistRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Artist not found"));
    return artistMapper.artistToArtistDTO(artist);
  }


  public ArtistEntity createArtist(ArtistDTO artistDTO) {
    Optional<ArtistEntity> existingArtist = artistRepository.findByName(artistDTO.getName());

    if (existingArtist.isPresent()) {
      throw new RuntimeException("Artist already exists.");
    } else {
      ArtistEntity artistEntity = artistMapper.artistDTOToArtist(artistDTO);
      return artistRepository.save(artistEntity);
    }
  }


    public ArtistEntity updateArtist (ArtistDTO artistDTO){
      ArtistEntity existingArtist = artistRepository.findById(artistDTO.getId())
              .orElseThrow(() -> new NotFoundException("Artist not found"));

      existingArtist.setName(artistDTO.getName());

      return artistRepository.save(existingArtist);
    }

    public void deleteArtist (UUID id){
      if (!artistRepository.existsById(id)) {
        throw new NotFoundException("Artist not found");
      }
      artistRepository.deleteById(id);
    }

}
