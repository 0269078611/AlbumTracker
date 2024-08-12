package com.lucic.albumtracker.entity;
import jakarta.persistence.*;

import java.util.Date;

import java.util.UUID;

@Entity
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
    private Date reviewDate;

}
