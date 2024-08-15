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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;


    @ManyToMany(mappedBy = "artists")
    private Set<AlbumEntity> albums = new HashSet<>();
}
