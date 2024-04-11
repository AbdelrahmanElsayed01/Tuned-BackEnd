package com.example.tuned.Controller;

import com.example.tuned.Business.Interfaces.topArtists.getTopArtists;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@CrossOrigin(origins = "http://localhost:5173/")
@RestController
@RequestMapping("/top")
@AllArgsConstructor
public class topArtistsController {
    private final getTopArtists getTopArtists;


    @GetMapping
    public List<Object[]> getTopArtists() {
        List<Object[]> topArtistsWithSongCount = getTopArtists.topArtists();

        if (Objects.nonNull(topArtistsWithSongCount) && !topArtistsWithSongCount.isEmpty()) {
            return topArtistsWithSongCount;
        } else {
            return List.of();
        }
    }
}
