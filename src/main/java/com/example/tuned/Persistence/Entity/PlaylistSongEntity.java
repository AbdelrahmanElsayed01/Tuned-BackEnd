package com.example.tuned.Persistence.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "playlist_song")
public class PlaylistSongEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private Long user;

    @ManyToOne
    private SongEntity songs;

    @ManyToOne
    private PlaylistEntity playlists;
}
