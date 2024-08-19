package com.lucic.albumtracker.dto;


import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
public class ArtistDTO {
    private UUID id;
    private String name;
    private String bio;
    private Set<UUID> albumIds;
    private Set<UUID> songIds;
}
