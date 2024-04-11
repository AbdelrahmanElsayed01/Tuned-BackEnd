package com.example.tuned.TopArtists;

import com.example.tuned.Business.Impl.topArtistsImpl.getTopArtistsImpl;
import com.example.tuned.Persistence.PlaylistSongRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
class GetTopArtistsUnitTests {
    @Mock
    private PlaylistSongRepository playlistSongRepository;

    @InjectMocks
    private getTopArtistsImpl getTopArtists;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testTopArtistsWhenDataExists() {

        List<Object[]> expectedTopArtists = new ArrayList<>();
        expectedTopArtists.add(new Object[]{"Artist1", 10L});
        expectedTopArtists.add(new Object[]{"Artist2", 8L});

        when(playlistSongRepository.findTopArtists()).thenReturn(expectedTopArtists);
        List<Object[]> topArtists = getTopArtists.topArtists();

        assertEquals(expectedTopArtists.size(), topArtists.size());
        for (Object[] artistData : expectedTopArtists) {
            assertTrue(topArtists.contains(artistData)); // Ensure each artist data is present in the result
        }
    }

    @Test
    void testTopArtistsWhenNoData() {

        List<Object[]> expectedTopArtists = new ArrayList<>();

        when(playlistSongRepository.findTopArtists()).thenReturn(expectedTopArtists);
        List<Object[]> topArtists = getTopArtists.topArtists();

        assertEquals(expectedTopArtists.size(), topArtists.size());
        assertTrue(topArtists.isEmpty());
    }
}
