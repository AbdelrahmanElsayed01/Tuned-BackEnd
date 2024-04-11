package com.example.tuned.Business.Impl.SongUseCasesImpl;

import com.example.tuned.Business.Interfaces.SongInterfaces.GetSongByTitleUseCase;
import com.example.tuned.Domain.Song.Song;
import com.example.tuned.Persistence.SongRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class GetSongByTitleUseCaseImpl implements GetSongByTitleUseCase {
    private final SongRepository songRepository;


    @Transactional
    @Override
    public Optional<Song> GetSongByTitle(String title) {
        return songRepository.findByTitle(title).map(SongConvertor::convert);
    }
}
