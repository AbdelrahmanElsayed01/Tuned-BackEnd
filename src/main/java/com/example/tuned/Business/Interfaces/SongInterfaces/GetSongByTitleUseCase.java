package com.example.tuned.Business.Interfaces.SongInterfaces;

import com.example.tuned.Domain.Song.Song;
import java.util.Optional;

public interface GetSongByTitleUseCase {
    Optional<Song> GetSongByTitle(String title);
}
