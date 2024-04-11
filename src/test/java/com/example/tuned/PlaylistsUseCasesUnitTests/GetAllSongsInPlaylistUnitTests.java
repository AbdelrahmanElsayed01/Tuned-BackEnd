package com.example.tuned.PlaylistsUseCasesUnitTests;

import com.example.tuned.Business.Impl.PlaylistUseCaseImpl.GetAllSongsInPlaylistUseCaseImpl;
import com.example.tuned.Domain.Playlist.GetAllSongsInPlaylistRequest;
import com.example.tuned.Domain.Song.Song;
import com.example.tuned.Persistence.Entity.PlaylistEntity;
import com.example.tuned.Persistence.Entity.PlaylistSongEntity;
import com.example.tuned.Persistence.Entity.SongEntity;
import com.example.tuned.Persistence.Entity.UserEntity;
import com.example.tuned.Persistence.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
class GetAllSongsInPlaylistUnitTests {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private GetAllSongsInPlaylistUseCaseImpl playlistUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void givenValidUserId_whenGettingAllSongsInUserPlaylist_thenReturnListOfSongs() {
        // Arrange
        long userId = 1L;
        GetAllSongsInPlaylistRequest request = new GetAllSongsInPlaylistRequest(userId);
        PlaylistEntity playlistEntity = new PlaylistEntity();

        PlaylistSongEntity playlistSong1 = new PlaylistSongEntity();
        PlaylistSongEntity playlistSong2 = new PlaylistSongEntity();

        SongEntity song1 = new SongEntity();
        SongEntity song2 = new SongEntity();

        playlistSong1.setSongs(song1);
        playlistSong2.setSongs(song2);

        playlistEntity.setPlaylistSongs(Collections.emptyList());

        UserEntity userEntity = new UserEntity();
        userEntity.setPlaylist(playlistEntity);

        when(userRepository.findById(userId)).thenReturn(java.util.Optional.of(userEntity));

        // Act
        List<Song> result = playlistUseCase.getAllSongsInUserPlaylist(request);

        // Assert
        assertEquals(0, result.size());
    }


}
