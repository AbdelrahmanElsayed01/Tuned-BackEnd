package com.example.tuned.Persistence;

import com.example.tuned.Persistence.Entity.PlaylistEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PlaylistRepository extends JpaRepository<PlaylistEntity, Long> {

    PlaylistEntity save(PlaylistEntity playlist);
    Optional<PlaylistEntity> findById(Long id);

    @Query("SELECT p.id FROM PlaylistEntity p WHERE p.user.id = :userId")
    Long getPlaylistIdByUserId(@Param("userId") Long userId);
}
