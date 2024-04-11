package com.example.tuned.SongUseCasesUnitTests;

import com.example.tuned.Business.Impl.SongUseCasesImpl.GetSongByTitleUseCaseImpl;
import com.example.tuned.Domain.Song.Song;
import com.example.tuned.Persistence.Entity.SongEntity;
import com.example.tuned.Persistence.SongRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class GetSongByTitleUseCaseUnitTests {
    @Mock
    private SongRepository songRepository;

    private GetSongByTitleUseCaseImpl getSongByTitleUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        getSongByTitleUseCase = new GetSongByTitleUseCaseImpl(songRepository);
    }

    @Test
    void testGetSongByTitle() {
        // Arrange
        String expectedTitle = "Song 1";
        SongEntity songEntity = SongEntity.builder()
                .songId(1L)
                .title(expectedTitle)
                .artist("Artist 1")
                .duration("300")
                .build();

        when(songRepository.findByTitle(expectedTitle)).thenReturn(Optional.of(songEntity));

        // Act
        Optional<Song> result = getSongByTitleUseCase.GetSongByTitle(expectedTitle);

        // Assert
        assertTrue(result.isPresent());

        Song actualSong = result.get();

        assertEquals(expectedTitle, actualSong.getTitle());
        // I can add more assertions here maybe
    }

    @Test
    void testGetSongByTitleSongNotFound() {
        // Arrange
        String expectedTitle = "Non-Existent Song";

        when(songRepository.findByTitle(expectedTitle)).thenReturn(Optional.empty());

        // Act
        Optional<Song> result = getSongByTitleUseCase.GetSongByTitle(expectedTitle);

        // Assert
        verify(songRepository, times(1)).findByTitle(expectedTitle);
        assertTrue(result.isEmpty());
    }
}
