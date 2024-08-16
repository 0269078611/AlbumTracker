package com.lucic.albumtracker.entity;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Comment;

import java.util.Date;

import java.util.UUID;

@Entity
@Data
public class ReviewEntity {
    @Id
    @GeneratedValue
    @Column(name = "review_id")
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "album_id")
    private AlbumEntity album;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
    @Column(name = "rating")
    private int rating;
    @Column(name="comment")
    private String comment;

}
