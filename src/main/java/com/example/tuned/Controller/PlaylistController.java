package com.example.tuned.Controller;

import com.example.tuned.Business.Interfaces.PlaylistInterfaces.AddSongToPlaylistUseCase;
import com.example.tuned.Business.Interfaces.PlaylistInterfaces.DeleteSongFromPlaylistUseCase;
import com.example.tuned.Business.Interfaces.PlaylistInterfaces.GetAllSongsInPlaylistUseCase;
import com.example.tuned.Business.Interfaces.PlaylistInterfaces.GetPlaylistIdByUserId;
import com.example.tuned.Configuration.Security.token.AccessToken;
import com.example.tuned.Configuration.Security.token.AccessTokenDecoder;
import com.example.tuned.Domain.Playlist.AddSongToPlaylistRequest;
import com.example.tuned.Domain.Playlist.DeleteSongFromPlaylistRequest;
import com.example.tuned.Domain.Playlist.GetAllSongsInPlaylistRequest;
import com.example.tuned.Domain.Song.Song;
import jakarta.annotation.security.RolesAllowed;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173/")
@RestController
@RequestMapping("/playlists")
@AllArgsConstructor
public class PlaylistController {
    private final AddSongToPlaylistUseCase addSongToPlaylistUseCase;
    private final GetAllSongsInPlaylistUseCase getAllSongsInUserPlaylist;
    private final DeleteSongFromPlaylistUseCase deleteSongFromPlaylistUseCase;
    private final GetPlaylistIdByUserId getPlaylistIdByUserId;
    private final AccessTokenDecoder accessTokenDecoder;

    @RolesAllowed("premium")
    @PostMapping("/add")
    public ResponseEntity<String> addSongToPlaylist(
            @RequestBody AddSongToPlaylistRequest request,
            @RequestHeader HttpHeaders headers) {

        String authorizationHeader = headers.getFirst(HttpHeaders.AUTHORIZATION);

        // Check if the Authorization header is present
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            // Extract the token value by removing the "Bearer " prefix
            String accessToken = authorizationHeader.substring(7);

            // Set the token in the request
            request.setAccessToken(accessToken);

            addSongToPlaylistUseCase.addSongToPlaylist(request);
            return ResponseEntity.ok("Song added to playlist successfully");
        } else {
            // Handle the case when the Authorization header is missing or doesn't have the expected format
            return ResponseEntity.badRequest().body("Invalid or missing Authorization header");
        }
    }

    @RolesAllowed("premium")
    @GetMapping("/songs")
    public ResponseEntity<List<Song>> getAllSongsInUserPlaylist(@RequestHeader HttpHeaders headers) {
        String authorizationHeader = headers.getFirst(HttpHeaders.AUTHORIZATION);
        // Extract the token value by removing the "Bearer " prefix
        String accessToken = authorizationHeader.substring(7);
        AccessToken accessToken1 = accessTokenDecoder.decode(accessToken);

        List<Song> response = getAllSongsInUserPlaylist.getAllSongsInUserPlaylist(GetAllSongsInPlaylistRequest.builder().userId(accessToken1.getUserId()).build());
        return ResponseEntity.ok(response);
    }

    @RolesAllowed("premium")
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteSongFromPlaylist(@RequestParam Long playlistId,@RequestParam Long songId) {
        DeleteSongFromPlaylistRequest request = DeleteSongFromPlaylistRequest.builder().playlistId(playlistId).songId(songId).build();
        deleteSongFromPlaylistUseCase.deleteSongFromPlaylist(request);
        return ResponseEntity.ok("Song deleted from playlist successfully");
    }

    @GetMapping("/playlistId")
    public ResponseEntity<Long> getPlaylistIdByUserId(@RequestHeader HttpHeaders headers) {

              String authorizationHeader = headers.getFirst(HttpHeaders.AUTHORIZATION);
            // Extract the token value by removing the "Bearer " prefix
            String accessToken = authorizationHeader.substring(7);
            AccessToken accessToken1 = accessTokenDecoder.decode(accessToken);
            // Set the token in the request
            Long playlistId = getPlaylistIdByUserId.getPlaylistIdByUserId(accessToken1.getUserId());

            if (playlistId != null) {
                return ResponseEntity.ok(playlistId);
            } else {
                return ResponseEntity.notFound().build();
            }



    }

}
