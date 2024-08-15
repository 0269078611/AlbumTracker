package com.lucic.albumtracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.lucic.albumtracker.entity.ArtistEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ArtistRepository extends JpaRepository<ArtistEntity, UUID> {
    Optional<ArtistEntity> findByName(String name);

}
