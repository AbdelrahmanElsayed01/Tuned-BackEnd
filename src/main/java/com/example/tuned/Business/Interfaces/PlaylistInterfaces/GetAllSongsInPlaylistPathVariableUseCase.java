package com.example.tuned.Business.Interfaces.PlaylistInterfaces;

import com.example.tuned.Domain.Song.Song;
import java.util.List;

public interface GetAllSongsInPlaylistPathVariableUseCase {
    List<Song> getAllSongsInUserPlaylist(Long  userId);
}
