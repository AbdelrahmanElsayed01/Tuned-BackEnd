package com.example.tuned.PlaylistsUseCasesUnitTests;

import com.example.tuned.Business.Impl.PlaylistUseCaseImpl.DeleteSongFromPlaylistUseCaseImpl;
import com.example.tuned.Domain.Playlist.DeleteSongFromPlaylistRequest;
import com.example.tuned.Persistence.Entity.PlaylistEntity;
import com.example.tuned.Persistence.Entity.PlaylistSongEntity;
import com.example.tuned.Persistence.Entity.SongEntity;
import com.example.tuned.Persistence.PlaylistRepository;
import com.example.tuned.Persistence.PlaylistSongRepository;
import com.example.tuned.Persistence.SongRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeleteSongFromPlaylistUseCaseUnitTests {
    @Mock
    private PlaylistRepository playlistRepository;

    @Mock
    private SongRepository songRepository;

    @Mock
    private PlaylistSongRepository playlistSongRepository;

    @InjectMocks
    private DeleteSongFromPlaylistUseCaseImpl deleteSongFromPlaylistUseCase;

    @Test
    void deleteExistingSongFromPlaylist_Successfully() {
        DeleteSongFromPlaylistRequest request = new DeleteSongFromPlaylistRequest(1L, 1L);

        PlaylistEntity playlistEntity = new PlaylistEntity();
        playlistEntity.setPlaylistId(1L);

        SongEntity songEntity = new SongEntity();
        songEntity.setSongId(1L);

        PlaylistSongEntity playlistSongEntity = new PlaylistSongEntity();
        playlistSongEntity.setPlaylists(playlistEntity);
        playlistSongEntity.setSongs(songEntity);

        playlistEntity.getPlaylistSongs().add(playlistSongEntity);

        when(playlistRepository.findById(request.getPlaylistId())).thenReturn(Optional.of(playlistEntity));
        when(songRepository.findById(request.getSongId())).thenReturn(Optional.of(songEntity));
        doNothing().when(playlistSongRepository).delete(any(PlaylistSongEntity.class));

        deleteSongFromPlaylistUseCase.deleteSongFromPlaylist(request);


        verify(playlistRepository, times(1)).findById(request.getPlaylistId());
        verify(songRepository, times(1)).findById(request.getSongId());


        verify(playlistSongRepository, times(1)).delete(any(PlaylistSongEntity.class));


        assertFalse(playlistEntity.getPlaylistSongs().contains(playlistSongEntity));
    }

    @Test
    void deleteNonExistingSongFromPlaylist_NoChanges() {
        DeleteSongFromPlaylistRequest request = new DeleteSongFromPlaylistRequest(1L, 1L);

        PlaylistEntity playlistEntity = new PlaylistEntity();
        playlistEntity.setPlaylistId(1L);

        SongEntity songEntity = new SongEntity();
        songEntity.setSongId(1L);

        PlaylistSongEntity playlistSongEntity = new PlaylistSongEntity();
        playlistSongEntity.setPlaylists(playlistEntity);
        playlistSongEntity.setSongs(songEntity);


        when(playlistRepository.findById(request.getPlaylistId())).thenReturn(Optional.of(playlistEntity));
        when(songRepository.findById(request.getSongId())).thenReturn(Optional.of(songEntity));

        deleteSongFromPlaylistUseCase.deleteSongFromPlaylist(request);


        verify(playlistRepository, times(1)).findById(request.getPlaylistId());
        verify(songRepository, times(1)).findById(request.getSongId());


        verify(playlistSongRepository, never()).delete(any(PlaylistSongEntity.class));
    }
}
