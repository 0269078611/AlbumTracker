package com.lucic.albumtracker.entity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
public class SongEntity {
    @Id
    @GeneratedValue
    private UUID id;

    private String title;
    private int trackNumber;
    private String duration;

    @ManyToOne
    @JoinColumn(name = "album_id", nullable = false)
    private AlbumEntity album;

}
