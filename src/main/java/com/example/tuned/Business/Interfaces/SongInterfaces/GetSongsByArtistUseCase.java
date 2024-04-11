package com.example.tuned.Business.Interfaces.SongInterfaces;

import com.example.tuned.Domain.Song.GetSongsByArtistRequest;
import com.example.tuned.Domain.Song.GetSongsByArtistResponse;

public interface GetSongsByArtistUseCase {
    GetSongsByArtistResponse GetSongsByArtist(GetSongsByArtistRequest request);
}
