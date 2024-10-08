package com.lucic.albumtracker.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
public class AlbumEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "album_id")
    private UUID id;
    @Column(name = "title")
    private String title;
    @ManyToOne
    @JoinColumn(name = "genre_id")
    private GenreEntity genre;
    @Column(name = "release_date")
    private String releaseDate;
    @Column(name = "description")
    private String description;
    @Column(name = "rating")

    private double rating;

    @ManyToMany(fetch =FetchType.EAGER)
    @JoinTable(
            name = "album_artist",
            joinColumns = @JoinColumn(name = "album_id"),
            inverseJoinColumns = @JoinColumn(name = "artist_id")
    )
    @JsonIgnore
    private Set<ArtistEntity> artists = new HashSet<>();

    @OneToMany(mappedBy = "album", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<SongEntity> songs = new HashSet<>();

    @OneToMany(mappedBy = "album", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<ReviewEntity> reviews = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AlbumEntity that = (AlbumEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
