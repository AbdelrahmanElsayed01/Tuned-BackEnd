package com.example.tuned.Controller;

import com.example.tuned.Business.Interfaces.SongInterfaces.GetSongByTitleUseCase;
import com.example.tuned.Business.Interfaces.SongInterfaces.GetSongsByArtistUseCase;
import com.example.tuned.Business.Interfaces.SongInterfaces.GetSongsUseCase;
import com.example.tuned.Business.Interfaces.SongInterfaces.SaveSongUseCase;
import com.example.tuned.Domain.Song.*;
import com.example.tuned.Persistence.Entity.SongEntity;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@CrossOrigin(origins = "http://localhost:5173/")
@RestController
@RequestMapping("/songs")
@AllArgsConstructor
public class SongsController {
    private final GetSongsUseCase getSongsUseCase;
    private final GetSongsByArtistUseCase getSongsByArtistUseCase;
    private final GetSongByTitleUseCase getSongByTitleUseCase;
    private final SaveSongUseCase saveSongUseCase;

    @GetMapping
    public ResponseEntity<GetSongsResponse> GetAllSongs(){
        return ResponseEntity.ok(getSongsUseCase.AllSongs());
    }


    @GetMapping("/artist/{artist}")
    public ResponseEntity<GetSongsByArtistResponse> GetSongsByArtist(@PathVariable String artist){
        GetSongsByArtistRequest request = GetSongsByArtistRequest.builder().artist(artist).build();
        GetSongsByArtistResponse response = getSongsByArtistUseCase.GetSongsByArtist(request);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/{title}")
    public ResponseEntity<Song> GetSongByTitle(@PathVariable String title){
        final Optional<Song> optionalSong = getSongByTitleUseCase.GetSongByTitle(title);
        if(optionalSong.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(optionalSong.get());
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveSong(@RequestBody SaveSongRequest songRequest) {
        SongEntity savedSong = saveSongUseCase.saveSong(songRequest);

        if(savedSong != null) {
            return ResponseEntity.ok( savedSong.getSongId().toString());
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save song");
        }
    }

}
