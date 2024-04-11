package com.example.tuned.Business.Impl.topArtistsImpl;

import com.example.tuned.Business.Interfaces.topArtists.getTopArtists;
import com.example.tuned.Persistence.PlaylistSongRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class getTopArtistsImpl implements getTopArtists{
    private final PlaylistSongRepository playlistSongRepository;

    @Override
    public List<Object[]> topArtists() {
        return playlistSongRepository.findTopArtists();
    }
}
