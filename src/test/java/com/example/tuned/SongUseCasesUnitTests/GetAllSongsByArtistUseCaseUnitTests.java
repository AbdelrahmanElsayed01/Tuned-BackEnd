package com.example.tuned.SongUseCasesUnitTests;

import com.example.tuned.Business.Impl.SongUseCasesImpl.GetSongsByArtistUseCaseImpl;
import com.example.tuned.Domain.Song.GetSongsByArtistRequest;
import com.example.tuned.Domain.Song.GetSongsByArtistResponse;
import com.example.tuned.Persistence.Entity.SongEntity;
import com.example.tuned.Persistence.SongRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class GetAllSongsByArtistUseCaseUnitTests {
    @Mock
    private SongRepository songRepository;

    @InjectMocks
    private GetSongsByArtistUseCaseImpl getSongsByArtistUseCase;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetSongsByArtistWithValidArtist() {
        String artist = "Eminem";
        List<SongEntity> mockedSongs = createMockedSongs(artist);
        when(songRepository.findAllByArtist(artist)).thenReturn(mockedSongs);

        GetSongsByArtistRequest request = new GetSongsByArtistRequest();
        request.setArtist(artist);


        GetSongsByArtistResponse response = getSongsByArtistUseCase.GetSongsByArtist(request);


        assertNotNull(response);
        assertEquals(response.getSongs().size(), mockedSongs.size());

        for (int i = 0; i < mockedSongs.size(); i++) {
            assertEquals(mockedSongs.get(i).getTitle(), response.getSongs().get(i).getTitle());
            assertEquals(mockedSongs.get(i).getArtist(), response.getSongs().get(i).getArtist());

        }
    }

    @Test
    void testGetSongsByArtistWithEmptyArtist() {
        List<SongEntity> mockedSongs = createMockedSongs("Eminem");
        when(songRepository.findAll()).thenReturn(mockedSongs);

        GetSongsByArtistRequest request = new GetSongsByArtistRequest();

        GetSongsByArtistResponse response = getSongsByArtistUseCase.GetSongsByArtist(request);

        assertNotNull(response);
        assertEquals(response.getSongs().size(), mockedSongs.size());

        for (int i = 0; i < mockedSongs.size(); i++) {
            assertEquals(mockedSongs.get(i).getTitle(), response.getSongs().get(i).getTitle());
            assertEquals(mockedSongs.get(i).getArtist(), response.getSongs().get(i).getArtist());
        }
    }

    private List<SongEntity> createMockedSongs(String artist) {
        List<SongEntity> mockedSongs = new ArrayList<>();
        mockedSongs.add(
                SongEntity.builder()
                        .songId(1L)
                        .title("lose yourself")
                        .artist(artist)
                        .duration("300")
                        .build()
        );
        mockedSongs.add(
                SongEntity.builder()
                        .songId(2L)
                        .title("Stan")
                        .artist(artist)
                        .duration("320")
                        .build()
        );
        return mockedSongs;
    }
}
