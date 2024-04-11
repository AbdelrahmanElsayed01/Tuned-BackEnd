package com.example.tuned.Persistence;

import com.example.tuned.Persistence.Entity.PlaylistSongEntity;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlaylistSongRepository extends JpaRepository<PlaylistSongEntity, Long> {
    @NotNull PlaylistSongEntity save(@NotNull PlaylistSongEntity playlistSong);

    @Query("SELECT ps.songs.artist, COUNT(ps.id) as song_count " +
            "FROM PlaylistSongEntity ps " +
            "JOIN ps.songs s " +
            "GROUP BY ps.songs.artist " +
            "ORDER BY song_count DESC " +
            "LIMIT 5")
    List<Object[]> findTopArtists();

}
