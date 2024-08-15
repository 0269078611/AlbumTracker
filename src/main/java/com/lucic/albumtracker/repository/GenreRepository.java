package com.lucic.albumtracker.repository;

import com.lucic.albumtracker.entity.GenreEntity;
import com.lucic.albumtracker.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface GenreRepository extends JpaRepository<GenreEntity, UUID> {
    Optional<GenreEntity> findByName(String name);
}
