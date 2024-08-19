package com.lucic.albumtracker.service;


import com.lucic.albumtracker.entity.ArtistEntity;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface ArtistService {

    Set<ArtistEntity> getAllArtists();

    ArtistEntity getArtistById(UUID id);

    ArtistEntity createArtist(ArtistEntity artistEntity);

    ArtistEntity updateArtist(ArtistEntity artistEntity);

    void deleteArtist(UUID id);
}
