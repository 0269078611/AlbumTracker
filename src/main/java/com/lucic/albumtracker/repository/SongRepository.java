package com.lucic.albumtracker.repository;

import com.lucic.albumtracker.entity.SongEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface SongRepository extends JpaRepository<SongEntity, UUID> {
    List<SongEntity> findByAlbumId(UUID albumId);
}
