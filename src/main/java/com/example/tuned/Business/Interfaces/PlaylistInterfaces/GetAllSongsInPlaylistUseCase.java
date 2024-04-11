package com.example.tuned.Business.Interfaces.PlaylistInterfaces;

import com.example.tuned.Domain.Playlist.GetAllSongsInPlaylistRequest;
import com.example.tuned.Domain.Song.Song;

import java.util.List;

public interface GetAllSongsInPlaylistUseCase {
    List<Song> getAllSongsInUserPlaylist(GetAllSongsInPlaylistRequest request);
}
