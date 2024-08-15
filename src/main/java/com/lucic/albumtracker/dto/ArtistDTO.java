package com.lucic.albumtracker.dto;


import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
public class ArtistDTO {
    private UUID id;
    private String name;
    private Set<AlbumDTO> albums;
}
