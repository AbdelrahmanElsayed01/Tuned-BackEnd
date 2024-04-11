package com.example.tuned.Business.Impl.SongUseCasesImpl;

import com.example.tuned.Domain.Song.Song;
import com.example.tuned.Persistence.Entity.SongEntity;

public class SongConvertor {

    private  SongConvertor(){

    }

    public static Song convert(SongEntity song){
        return Song.builder()
                .id(song.getSongId())
                .title(song.getTitle())
                .artist(song.getArtist())
                .uri(song.getUri())
                .image(song.getImage())
                .duration(song.getDuration())
                .build();
    }
}
