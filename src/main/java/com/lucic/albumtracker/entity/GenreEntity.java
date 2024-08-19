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
    @Column(name="genre_id")
    private UUID id;
    @Column(name="name")
    private String name;

    @OneToMany(mappedBy = "genre")
    private Set<AlbumEntity> albums = new HashSet<>();
}