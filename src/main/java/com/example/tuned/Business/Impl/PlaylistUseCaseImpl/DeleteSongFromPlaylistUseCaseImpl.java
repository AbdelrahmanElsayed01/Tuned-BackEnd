package com.example.tuned.Business.Impl.PlaylistUseCaseImpl;

import com.example.tuned.Business.Interfaces.PlaylistInterfaces.DeleteSongFromPlaylistUseCase;
import com.example.tuned.Domain.Playlist.DeleteSongFromPlaylistRequest;
import com.example.tuned.Persistence.Entity.PlaylistEntity;
import com.example.tuned.Persistence.Entity.PlaylistSongEntity;
import com.example.tuned.Persistence.Entity.SongEntity;
import com.example.tuned.Persistence.PlaylistRepository;
import com.example.tuned.Persistence.PlaylistSongRepository;
import com.example.tuned.Persistence.SongRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteSongFromPlaylistUseCaseImpl implements DeleteSongFromPlaylistUseCase {
    private final PlaylistRepository playlistRepository;
    private final SongRepository songRepository;
    private final PlaylistSongRepository playlistSongRepository;

    @Override
    @Transactional
    public void deleteSongFromPlaylist(DeleteSongFromPlaylistRequest request) {
        PlaylistEntity playlist = playlistRepository.findById(request.getPlaylistId())
                .orElseThrow(() -> new RuntimeException("Playlist not found"));

        SongEntity song = songRepository.findById(request.getSongId())
                .orElseThrow(() -> new RuntimeException("Song not found"));

        PlaylistSongEntity playlistSongToRemove = findPlaylistSong(playlist, song);

        if (playlistSongToRemove != null) {
            playlist.getPlaylistSongs().remove(playlistSongToRemove);
            playlistSongRepository.delete(playlistSongToRemove);
        }
    }

    private PlaylistSongEntity findPlaylistSong(PlaylistEntity playlist, SongEntity song) {
        return playlist.getPlaylistSongs().stream()
                .filter(playlistSong -> playlistSong.getSongs().equals(song))
                .findFirst()
                .orElse(null);
    }
}
