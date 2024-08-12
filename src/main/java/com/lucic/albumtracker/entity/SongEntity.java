package com.lucic.albumtracker.entity;
import jakarta.persistence.*;

import javax.annotation.processing.Generated;
import java.util.UUID;

@Entity
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
