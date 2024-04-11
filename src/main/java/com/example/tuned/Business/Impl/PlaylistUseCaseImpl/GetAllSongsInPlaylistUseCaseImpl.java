package com.example.tuned.Business.Impl.PlaylistUseCaseImpl;

import com.example.tuned.Business.Impl.UserUseCaseImpl.UserConvertor;
import com.example.tuned.Business.Interfaces.PlaylistInterfaces.GetAllSongsInPlaylistUseCase;
import com.example.tuned.Domain.Playlist.GetAllSongsInPlaylistRequest;
import com.example.tuned.Domain.Playlist.Playlist;
import com.example.tuned.Domain.Playlist.PlaylistSong;
import com.example.tuned.Domain.Song.Song;
import com.example.tuned.Domain.User.User;
import com.example.tuned.Persistence.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GetAllSongsInPlaylistUseCaseImpl implements GetAllSongsInPlaylistUseCase {
    private final UserRepository userRepository;
    @Override
    @Transactional
    public List<Song> getAllSongsInUserPlaylist(GetAllSongsInPlaylistRequest request) {

        User newUser =  UserConvertor.convert(userRepository.findById(request.getUserId()).get());

        Playlist playlist = newUser.getPlaylist();

        if (playlist != null) {
            List<PlaylistSong> playlistSongs = playlist.getPlaylistSongs();

            return playlistSongs.stream()
                    .map(PlaylistSong::getSongs)
                    .collect(Collectors.toList());


        } else {
            return Collections.emptyList();
        }


    }
}
