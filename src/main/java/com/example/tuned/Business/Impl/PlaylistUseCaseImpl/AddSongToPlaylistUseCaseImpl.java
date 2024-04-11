package com.example.tuned.Business.Impl.PlaylistUseCaseImpl;

import com.example.tuned.Business.Interfaces.PlaylistInterfaces.AddSongToPlaylistUseCase;
import com.example.tuned.Configuration.Security.token.AccessToken;
import com.example.tuned.Configuration.Security.token.AccessTokenDecoder;
import com.example.tuned.Domain.Playlist.AddSongToPlaylistRequest;
import com.example.tuned.Persistence.Entity.PlaylistEntity;
import com.example.tuned.Persistence.Entity.PlaylistSongEntity;
import com.example.tuned.Persistence.Entity.SongEntity;
import com.example.tuned.Persistence.Entity.UserEntity;
import com.example.tuned.Persistence.PlaylistRepository;
import com.example.tuned.Persistence.PlaylistSongRepository;
import com.example.tuned.Persistence.SongRepository;
import com.example.tuned.Persistence.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AddSongToPlaylistUseCaseImpl implements AddSongToPlaylistUseCase {
    private final PlaylistRepository playlistRepository;
    private final AccessTokenDecoder accessTokenDecoder;
    private final SongRepository songRepository;
    private final PlaylistSongRepository playlistSongRepository;
    private final UserRepository userRepository; // Assuming you have a UserRepository

    @Override
    @Transactional
    public void addSongToPlaylist(AddSongToPlaylistRequest request) {
        PlaylistEntity playlist = playlistRepository.findById(request.getPlaylistId())
                .orElseThrow(() -> new RuntimeException("Playlist not found"));

        SongEntity song = songRepository.findById(request.getSongId())
                .orElseThrow(() -> new RuntimeException("Song not found"));

        if (!isSongAlreadyInPlaylist(playlist, song)) {
            PlaylistSongEntity playlistSong = new PlaylistSongEntity();
            playlistSong.setPlaylists(playlist);
            playlistSong.setSongs(song);

            System.out.println("getAccessToken: " + request.getAccessToken());

            AccessToken userId = accessTokenDecoder.decode(request.getAccessToken());
            System.out.println("User ID: " + userId.getUserId());

            UserEntity user = userRepository.findById(userId.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            playlistSong.setUser(user.getUserId());

            playlist.getPlaylistSongs().add(playlistSong);

            playlistSongRepository.save(playlistSong);
        }
    }

    private boolean isSongAlreadyInPlaylist(PlaylistEntity playlist, SongEntity song) {
        return playlist.getPlaylistSongs().stream()
                .anyMatch(playlistSong -> playlistSong.getSongs().equals(song));
    }
}

