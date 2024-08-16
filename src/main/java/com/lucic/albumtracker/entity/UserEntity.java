package com.lucic.albumtracker.entity;
import com.lucic.albumtracker.entity.enums.Role;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.List;
@Entity
@Data
@Table(name="user_table")
public class UserEntity {
    @Id
    @GeneratedValue
    @Column (name = "user_id")
    private UUID id;
    @Column(name = "username")
    private String username;
    @Column (name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<ReviewEntity> review = new HashSet<>();

    @Column (name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

}
