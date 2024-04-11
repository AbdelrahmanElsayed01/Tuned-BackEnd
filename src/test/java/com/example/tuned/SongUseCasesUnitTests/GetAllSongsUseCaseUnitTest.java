package com.example.tuned.SongUseCasesUnitTests;

import com.example.tuned.Business.Impl.SongUseCasesImpl.GetSongsUseCaseImpl;
import com.example.tuned.Domain.Song.GetSongsResponse;
import com.example.tuned.Persistence.Entity.SongEntity;
import com.example.tuned.Persistence.SongRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class GetAllSongsUseCaseUnitTest {

    @Mock
    private SongRepository songRepository;

    private GetSongsUseCaseImpl getSongsUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        getSongsUseCase = new GetSongsUseCaseImpl(songRepository);
    }

    @Test
    void testAllSongs() {
        // Arrange
        List<SongEntity> songEntities = Arrays.asList(
                SongEntity.builder()
                        .songId(1L)
                        .title("Song 1")
                        .artist("Artist 1")
                        .duration("300")
                        .build()
        );

        when(songRepository.findAll()).thenReturn(songEntities);

        // Act
        GetSongsResponse response = getSongsUseCase.AllSongs();

        // Assert
        assertEquals(songEntities.size(), response.getSongs().size());
    }
}
