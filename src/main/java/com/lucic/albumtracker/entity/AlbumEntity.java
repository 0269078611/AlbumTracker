package com.lucic.albumtracker.entity;

import jakarta.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
public class AlbumEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String title;
    private String artist;
    private String genre;
    private String releaseDate;
    private String description;
    private double rating;

    @OneToMany (mappedBy= "albumEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ReviewEntity> reviews;

    @OneToMany (mappedBy = "albumEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SongEntity> songs;

}
