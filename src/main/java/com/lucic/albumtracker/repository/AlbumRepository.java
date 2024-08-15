package com.lucic.albumtracker.repository;

import com.lucic.albumtracker.entity.AlbumEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface AlbumRepository extends JpaRepository<AlbumEntity, UUID> {

}
