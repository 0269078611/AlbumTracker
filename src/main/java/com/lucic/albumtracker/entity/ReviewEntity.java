package com.lucic.albumtracker.entity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

import java.util.UUID;

@Entity
@Data
public class ReviewEntity {
    @Id
    @GeneratedValue
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "album_id", nullable =false)
    private AlbumEntity album;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable =false)
    private UserEntity user;

    private int rating;

    private String comment;

}
