package com.lucic.albumtracker.repository;

import com.lucic.albumtracker.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity, UUID> {
}
