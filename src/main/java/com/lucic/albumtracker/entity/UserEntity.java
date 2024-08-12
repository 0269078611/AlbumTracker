package com.lucic.albumtracker.entity;
import com.lucic.albumtracker.entity.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.List;
@Entity
@Getter
@Setter
@Table(name="user")
public class UserEntity {
    @Id
    @GeneratedValue
    @Column
    private UUID id;
    @Column
    private String username;
    @Column
    private String email;
    @Column
    private String password;
    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<ReviewEntity> review = new HashSet<>();

    @Column
    @Enumerated(EnumType.STRING)
    private Role role;

}
