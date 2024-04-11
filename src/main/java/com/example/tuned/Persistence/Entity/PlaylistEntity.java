package com.example.tuned.Persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Playlist")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlaylistEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long playlistId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    @JsonIgnore
    private UserEntity user;

    @OneToMany(mappedBy = "playlists", cascade = CascadeType.ALL)
    private List<PlaylistSongEntity> playlistSongs = new ArrayList<>();
}

