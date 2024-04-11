package com.example.tuned.Domain.Playlist;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddSongToPlaylistRequest {
    private Long playlistId;
    private Long songId;
    private String accessToken;
}
