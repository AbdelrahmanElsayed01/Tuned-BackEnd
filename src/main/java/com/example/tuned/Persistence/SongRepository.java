package com.example.tuned.Persistence;

import com.example.tuned.Persistence.Entity.SongEntity;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SongRepository extends JpaRepository<SongEntity, Long> {

    @NotNull SongEntity save(@NotNull SongEntity song);
    @NotNull List<SongEntity> findAll();
    List<SongEntity> findAllByArtist(String artistName);
    Optional<SongEntity> findByTitle(String title);
    @NotNull Optional<SongEntity> findById(@NotNull Long songId);



}
