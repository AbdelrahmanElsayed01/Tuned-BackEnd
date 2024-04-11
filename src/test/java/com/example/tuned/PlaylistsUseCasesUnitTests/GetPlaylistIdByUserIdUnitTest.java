package com.example.tuned.PlaylistsUseCasesUnitTests;

import com.example.tuned.Business.Impl.PlaylistUseCaseImpl.GetPlaylistIdByUserIdImpl;
import com.example.tuned.Persistence.PlaylistRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

class GetPlaylistIdByUserIdUnitTest {
    @Mock
    private PlaylistRepository playlistRepository;

    @InjectMocks
    private GetPlaylistIdByUserIdImpl getPlaylistIdByUserId;

    public GetPlaylistIdByUserIdUnitTest() {
        MockitoAnnotations.openMocks(this);
        getPlaylistIdByUserId = new GetPlaylistIdByUserIdImpl(playlistRepository);
    }

    @Test
    void testGetPlaylistIdByUserId() {
        // Prepare test data
        Long userId = 1L; // Use any user ID for testing
        Long expectedPlaylistId = 100L; // Use any expected playlist ID for testing
        when(playlistRepository.getPlaylistIdByUserId(userId)).thenReturn(expectedPlaylistId);

        // Invoke the method being tested
        Long playlistId = getPlaylistIdByUserId.getPlaylistIdByUserId(userId);

        // Validate the result
        assertEquals(expectedPlaylistId, playlistId); // Ensure the returned playlist ID matches the expected value
    }

    @Test
    void testGetPlaylistIdByUserIdWhenNotFound() {
        // Prepare test data
        Long userId = 2L; // Use any user ID for testing
        when(playlistRepository.getPlaylistIdByUserId(userId)).thenReturn(null);

        // Invoke the method being tested
        Long playlistId = getPlaylistIdByUserId.getPlaylistIdByUserId(userId);

        // Validate the result
        assertNull(playlistId); // Ensure null is returned when playlist ID is not found
        // You can modify the assertion based on how you want to handle the case when the playlist ID is not found
    }
}
