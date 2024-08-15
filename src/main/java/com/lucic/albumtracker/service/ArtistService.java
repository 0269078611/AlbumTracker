package com.lucic.albumtracker.service;

import com.lucic.albumtracker.dto.ArtistDTO;
import com.lucic.albumtracker.entity.ArtistEntity;

import java.util.List;
import java.util.UUID;

public interface ArtistService {

    List<ArtistDTO> getAllArtists();

    ArtistDTO getArtistById(UUID id);

    ArtistEntity createArtist(ArtistDTO artistDTO);

    ArtistEntity updateArtist(ArtistDTO artistDTO);

    void deleteArtist(UUID id);
}
