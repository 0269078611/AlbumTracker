package com.lucic.albumtracker.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
public class AlbumEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String title;
    @ManyToOne
    @JoinColumn(name = "genre_id", nullable = false)
    private GenreEntity genre;
    private String releaseDate;
    private String description;
    private double rating;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity owner;

    @ManyToMany
    @JoinTable(
            name = "album_artist",
            joinColumns = @JoinColumn(name = "album_id"),
            inverseJoinColumns = @JoinColumn(name = "artist_id")
    )
    private Set<ArtistEntity> artists = new HashSet<>();

    @OneToMany(mappedBy = "album", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<SongEntity> songs = new HashSet<>();

}
