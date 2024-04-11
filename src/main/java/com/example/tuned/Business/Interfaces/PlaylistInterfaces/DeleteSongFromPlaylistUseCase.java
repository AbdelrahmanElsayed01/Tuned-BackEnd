package com.example.tuned.Business.Interfaces.PlaylistInterfaces;

import com.example.tuned.Domain.Playlist.DeleteSongFromPlaylistRequest;

public interface DeleteSongFromPlaylistUseCase {
    void deleteSongFromPlaylist(DeleteSongFromPlaylistRequest request);
}
