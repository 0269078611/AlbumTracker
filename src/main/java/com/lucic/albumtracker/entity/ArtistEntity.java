package com.lucic.albumtracker.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.*;

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
    @JsonIgnore
    private Set<AlbumEntity> albums = new HashSet<>();

    @ManyToMany(mappedBy = "artists")
    @JsonIgnore
    private Set<SongEntity> songs = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArtistEntity that = (ArtistEntity) o;
        return Objects.equals(id, that.id);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
