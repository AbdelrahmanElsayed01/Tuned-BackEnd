package com.example.tuned.Domain.Playlist;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeleteSongFromPlaylistRequest {
        private Long playlistId;
        private Long songId;
}
