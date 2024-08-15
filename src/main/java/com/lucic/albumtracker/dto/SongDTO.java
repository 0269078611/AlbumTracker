package com.lucic.albumtracker.dto;

import lombok.Data;

import java.util.UUID;
@Data
public class SongDTO {
    private UUID id;
    private String title;
    private int trackNumber;
    private String duration;
    private UUID albumId;
}
