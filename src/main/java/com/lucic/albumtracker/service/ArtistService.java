package com.lucic.albumtracker.service;

import com.lucic.albumtracker.dto.ArtistDTO;
import com.lucic.albumtracker.entity.ArtistEntity;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface ArtistService {

    Set<ArtistDTO> getAllArtists();

    ArtistDTO getArtistById(UUID id);

    ArtistDTO createArtist(ArtistDTO artistDTO);

    ArtistDTO updateArtist(ArtistDTO artistDTO);

    void deleteArtist(UUID id);
}
