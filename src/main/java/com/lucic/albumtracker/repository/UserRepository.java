package com.lucic.albumtracker.repository;
import com.lucic.albumtracker.entity.UserEntity;
import com.lucic.albumtracker.entity.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    Optional <UserEntity> findByUsername(String username);
    Optional <UserEntity> findByEmail(String email);

    long countByRole(Role admin);
}
