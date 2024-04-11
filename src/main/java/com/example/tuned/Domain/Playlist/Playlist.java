package com.example.tuned.Domain.Playlist;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Playlist {

    private Long playlistId;

    private List<PlaylistSong> playlistSongs = new ArrayList<>();

}
