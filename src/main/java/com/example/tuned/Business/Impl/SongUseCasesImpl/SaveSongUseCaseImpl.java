package com.example.tuned.Business.Impl.SongUseCasesImpl;

import com.example.tuned.Business.Interfaces.SongInterfaces.SaveSongUseCase;
import com.example.tuned.Domain.Song.SaveSongRequest;
import com.example.tuned.Persistence.Entity.SongEntity;
import com.example.tuned.Persistence.SongRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SaveSongUseCaseImpl implements SaveSongUseCase {

    private final SongRepository songRepository;
    @Override
    public SongEntity saveSong(SaveSongRequest songRequest) {
        SongEntity newSong = SongEntity.builder()
                .title(songRequest.getTitle())
                .artist(songRequest.getArtist())
                .uri(songRequest.getUri())
                .image(songRequest.getImage())
                .duration(songRequest.getDuration())
                .build();

        return songRepository.save(newSong);
    }
}
