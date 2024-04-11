package com.example.tuned.Persistence.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Song")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SongEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long songId;

    @NotBlank
    @Column(name = "title")
    private String title;

    @NotBlank
    @Column(name = "artist")
    private String artist;

    @NotBlank
    @Column(name = "uri")
    private String uri;

    @NotBlank
    @Column(name = "image")
    private String image;

    @NotBlank
    @Column(name = "duration")
    private String duration;

    @OneToMany(mappedBy = "songs", cascade = CascadeType.ALL)
    private List<PlaylistSongEntity> playlists = new ArrayList<>();
}
