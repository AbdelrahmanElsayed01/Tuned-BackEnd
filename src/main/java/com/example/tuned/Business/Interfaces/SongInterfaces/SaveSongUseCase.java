package com.example.tuned.Business.Interfaces.SongInterfaces;

import com.example.tuned.Domain.Song.SaveSongRequest;
import com.example.tuned.Persistence.Entity.SongEntity;

public interface SaveSongUseCase {
    SongEntity saveSong(SaveSongRequest songRequest);
}
