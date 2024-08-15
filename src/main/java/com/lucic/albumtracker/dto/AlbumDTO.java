package com.lucic.albumtracker.dto;

import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
public class AlbumDTO {
        private UUID id;
        private String title;
        private GenreDTO genre;
        private String releaseDate;
        private String description;
        private double rating;
        private UserDTO owner;
        private Set<ArtistDTO> artists;
        private Set<SongDTO> songs;


}
