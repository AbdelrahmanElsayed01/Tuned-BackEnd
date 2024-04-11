package com.example.tuned.Domain.Playlist;

import com.example.tuned.Domain.Song.Song;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlaylistSong {

    private Long id;

    private Song songs;
}
