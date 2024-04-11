package com.example.tuned.PlaylistsUseCasesUnitTests;

import com.example.tuned.Business.Impl.PlaylistUseCaseImpl.AddSongToPlaylistUseCaseImpl;
import com.example.tuned.Domain.Playlist.AddSongToPlaylistRequest;
import com.example.tuned.Persistence.Entity.PlaylistEntity;
import com.example.tuned.Persistence.Entity.PlaylistSongEntity;
import com.example.tuned.Persistence.Entity.SongEntity;
import com.example.tuned.Persistence.Entity.UserEntity;
import com.example.tuned.Persistence.PlaylistRepository;
import com.example.tuned.Persistence.PlaylistSongRepository;
import com.example.tuned.Persistence.SongRepository;
import com.example.tuned.Persistence.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AddSongToPlaylistUnitTests {
    @Mock
    private PlaylistRepository playlistRepository;

    @Mock
    private SongRepository songRepository;

    @Mock
    private PlaylistSongRepository playlistSongRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AddSongToPlaylistUseCaseImpl addSongToPlaylistUseCase;

    @Test
    void addSongToPlaylist_Successfully() {
        AddSongToPlaylistRequest request = new AddSongToPlaylistRequest(1L, 1L, 1L);

        PlaylistEntity playlistEntity = new PlaylistEntity();
        playlistEntity.setPlaylistId(1L);

        SongEntity songEntity = new SongEntity();
        songEntity.setSongId(1L);

        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(1L);

        when(playlistRepository.findById(request.getPlaylistId())).thenReturn(Optional.of(playlistEntity));
        when(songRepository.findById(request.getSongId())).thenReturn(Optional.of(songEntity));
        when(userRepository.findById(request.getUserId())).thenReturn(Optional.of(userEntity)); // Return user when requested

        when(playlistSongRepository.save(any(PlaylistSongEntity.class))).thenReturn(new PlaylistSongEntity());

        addSongToPlaylistUseCase.addSongToPlaylist(request);

        verify(playlistRepository, times(1)).findById(request.getPlaylistId());
        verify(songRepository, times(1)).findById(request.getSongId());
        verify(userRepository, times(1)).findById(request.getUserId()); // Verify user lookup
        verify(playlistSongRepository, times(1)).save(any(PlaylistSongEntity.class));
    }


    @Test
    void addSongToPlaylist_PlaylistNotFound_ThrowException() {
        AddSongToPlaylistRequest request = new AddSongToPlaylistRequest(1L, 1L, 1L);

        when(playlistRepository.findById(request.getPlaylistId())).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> addSongToPlaylistUseCase.addSongToPlaylist(request));

        verify(songRepository, never()).findById(anyLong());
        verify(playlistSongRepository, never()).save(any());
    }

    @Test
    void addSongToPlaylist_SongNotFound_ThrowException() {
        AddSongToPlaylistRequest request = new AddSongToPlaylistRequest(1L, 1L, 1L);

        PlaylistEntity playlistEntity = new PlaylistEntity();
        playlistEntity.setPlaylistId(1L);

        when(playlistRepository.findById(request.getPlaylistId())).thenReturn(Optional.of(playlistEntity));
        when(songRepository.findById(request.getSongId())).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> addSongToPlaylistUseCase.addSongToPlaylist(request));

        verify(playlistRepository, times(1)).findById(request.getPlaylistId());
        verify(playlistSongRepository, never()).save(any());
    }

    @Test
    void addDuplicateSongToPlaylist_NothingChanges() {
        AddSongToPlaylistRequest request = new AddSongToPlaylistRequest(1L, 1L, 1L);

        PlaylistEntity playlistEntity = new PlaylistEntity();
        playlistEntity.setPlaylistId(1L);

        SongEntity songEntity = new SongEntity();
        songEntity.setSongId(1L);

        PlaylistSongEntity existingPlaylistSong = new PlaylistSongEntity();
        existingPlaylistSong.setPlaylists(playlistEntity);
        existingPlaylistSong.setSongs(songEntity);

        playlistEntity.getPlaylistSongs().add(existingPlaylistSong);

        when(playlistRepository.findById(request.getPlaylistId())).thenReturn(Optional.of(playlistEntity));
        when(songRepository.findById(request.getSongId())).thenReturn(Optional.of(songEntity));

        addSongToPlaylistUseCase.addSongToPlaylist(request);

        verify(playlistRepository, times(1)).findById(request.getPlaylistId());
        verify(songRepository, times(1)).findById(request.getSongId());

        // Verify that save is never called on playlistSongRepository (because it's a duplicate)
        verify(playlistSongRepository, never()).save(any(PlaylistSongEntity.class));
    }

    @Test
    void testAddSongToPlaylistWithDuplicateSong() {
        long playlistId = 1L;
        long songId = 100L;
        long userId = 1L;

        PlaylistEntity playlistEntity = new PlaylistEntity();
        playlistEntity.setPlaylistId(playlistId);
        playlistEntity.setPlaylistSongs(new ArrayList<>());

        SongEntity songEntity = new SongEntity();
        songEntity.setSongId(songId);

        PlaylistSongEntity playlistSongEntity = new PlaylistSongEntity();
        playlistSongEntity.setPlaylists(playlistEntity);
        playlistSongEntity.setSongs(songEntity);

        UserEntity userEntity = new UserEntity(); // Create a user entity
        userEntity.setUserId(userId); // Set user ID

        when(playlistRepository.findById(playlistId)).thenReturn(Optional.of(playlistEntity));
        when(songRepository.findById(songId)).thenReturn(Optional.of(songEntity));
        when(userRepository.findById(userId)).thenReturn(Optional.of(userEntity)); // Return user when requested

        when(playlistSongRepository.save(any())).thenReturn(playlistSongEntity);

        // Adding the same song to the playlist twice
        AddSongToPlaylistRequest request = new AddSongToPlaylistRequest(playlistId, songId, userId);
        addSongToPlaylistUseCase.addSongToPlaylist(request);
        addSongToPlaylistUseCase.addSongToPlaylist(request);

        // Verifying that the song is added only once
        assertEquals(1, playlistEntity.getPlaylistSongs().size());
        verify(playlistSongRepository, times(1)).save(any());
    }


}
