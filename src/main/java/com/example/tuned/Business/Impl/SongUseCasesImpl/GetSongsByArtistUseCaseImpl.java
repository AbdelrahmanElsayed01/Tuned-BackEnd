package com.example.tuned.Business.Impl.SongUseCasesImpl;

import com.example.tuned.Business.Interfaces.SongInterfaces.GetSongsByArtistUseCase;
import com.example.tuned.Domain.Song.GetSongsByArtistRequest;
import com.example.tuned.Domain.Song.GetSongsByArtistResponse;
import com.example.tuned.Domain.Song.Song;
import com.example.tuned.Persistence.Entity.SongEntity;
import com.example.tuned.Persistence.SongRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@AllArgsConstructor
public class GetSongsByArtistUseCaseImpl implements GetSongsByArtistUseCase {
    private final SongRepository songRepository;
    @Transactional
    @Override
    public GetSongsByArtistResponse GetSongsByArtist(GetSongsByArtistRequest request) {
        List<SongEntity> Songs;
        if(StringUtils.hasText(request.getArtist())){
            Songs = songRepository.findAllByArtist(request.getArtist());
        }
        else{
            Songs = songRepository.findAll();
        }

        final GetSongsByArtistResponse response = new GetSongsByArtistResponse();
        List<Song> songs = Songs
                .stream()
                .map(SongConvertor::convert)
                .toList();
        response.setSongs(songs);
        return  response;
    }
}
