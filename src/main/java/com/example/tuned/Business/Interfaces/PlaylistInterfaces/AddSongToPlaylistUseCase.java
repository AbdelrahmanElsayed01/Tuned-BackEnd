package com.example.tuned.Business.Interfaces.PlaylistInterfaces;

import com.example.tuned.Domain.Playlist.AddSongToPlaylistRequest;

public interface AddSongToPlaylistUseCase {
    void addSongToPlaylist(AddSongToPlaylistRequest request);
}
