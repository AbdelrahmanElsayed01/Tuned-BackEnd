package com.example.tuned.Business.Impl.PlaylistUseCaseImpl;

import com.example.tuned.Domain.Playlist.Playlist;
import com.example.tuned.Persistence.Entity.PlaylistEntity;

public class PlaylistConverter {

    private PlaylistConverter(){}

    public static Playlist convert(PlaylistEntity playlist){
        return Playlist.builder()
                .playlistId(playlist.getPlaylistId())
                .playlistSongs(playlist.getPlaylistSongs().stream().map(PlaylistSongConverter::convert).toList())
                .build();
    }
}
