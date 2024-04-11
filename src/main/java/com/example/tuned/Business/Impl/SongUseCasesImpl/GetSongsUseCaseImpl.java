package com.example.tuned.Business.Impl.SongUseCasesImpl;

import com.example.tuned.Business.Interfaces.SongInterfaces.GetSongsUseCase;
import com.example.tuned.Domain.Song.GetSongsResponse;
import com.example.tuned.Domain.Song.Song;
import com.example.tuned.Persistence.SongRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetSongsUseCaseImpl implements GetSongsUseCase {
    private final SongRepository songRepository;


    @Transactional
    @Override
    public GetSongsResponse AllSongs() {
        List<Song> Songs = songRepository.findAll()
                .stream()
                .map(SongConvertor::convert)
                .toList();
        return GetSongsResponse.builder()
                .Songs(Songs)
                .build();
    }
}
