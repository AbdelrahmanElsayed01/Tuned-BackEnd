package com.example.tuned.Business.Impl.PlaylistUseCaseImpl;

import com.example.tuned.Business.Impl.SongUseCasesImpl.SongConvertor;
import com.example.tuned.Domain.Playlist.PlaylistSong;
import com.example.tuned.Persistence.Entity.PlaylistSongEntity;

public class PlaylistSongConverter {

    private  PlaylistSongConverter(){}

    public static PlaylistSong convert(PlaylistSongEntity playlistSong)
    {
        return PlaylistSong.builder()
                .id(playlistSong.getId())
                .songs(SongConvertor.convert(playlistSong.getSongs()))
                .build();
    }
}
