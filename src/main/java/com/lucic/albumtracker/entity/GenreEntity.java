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
public class GenreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="genre_id")
    private UUID id;
    @Column(name="name")
    private String name;

    @OneToMany(mappedBy = "genre")
    @JsonIgnore
    private Set<AlbumEntity> albums = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GenreEntity that = (GenreEntity) o;
        return Objects.equals(id, that.id);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}