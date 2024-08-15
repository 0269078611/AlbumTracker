package com.lucic.albumtracker.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
public class GenreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;

    @OneToMany(mappedBy = "genre", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<AlbumEntity> albums = new HashSet<>();
}