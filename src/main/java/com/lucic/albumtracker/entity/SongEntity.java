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
    @Column(name="song_id")
    private UUID id;
    @Column(name="title")
    private String title;
    @Column(name="trackNumber")
    private int trackNumber;
    @Column(name="duration")
    private String duration;

    @ManyToOne
    @JoinColumn(name = "album_id", nullable = false)
    private AlbumEntity album;

    @ManyToMany
    @JoinTable(
            name = "artist_song",
            joinColumns = @JoinColumn(name = "song_id"),
            inverseJoinColumns = @JoinColumn(name = "artist_id")
    )
    private Set<ArtistEntity> artists = new HashSet<>();

}
