package com.lucic.albumtracker.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
public class ArtistEntity {
    @Id
    @Column(name="artist_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(name="name")
    private String name;
    @Column(name="bio")
    private String bio;

    @ManyToMany(mappedBy = "artists")
    private Set<AlbumEntity> albums = new HashSet<>();

    @ManyToMany(mappedBy = "artists")
    private Set<SongEntity> songs = new HashSet<>();
}
