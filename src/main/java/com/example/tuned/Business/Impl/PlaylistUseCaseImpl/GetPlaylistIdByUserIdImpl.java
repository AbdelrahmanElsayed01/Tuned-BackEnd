package com.example.tuned.Business.Impl.PlaylistUseCaseImpl;

import com.example.tuned.Business.Interfaces.PlaylistInterfaces.GetPlaylistIdByUserId;
import com.example.tuned.Persistence.PlaylistRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetPlaylistIdByUserIdImpl implements GetPlaylistIdByUserId {
    private PlaylistRepository playlistRepository;


    @Override
    public Long getPlaylistIdByUserId(Long userId) {
        return playlistRepository.getPlaylistIdByUserId(userId);
    }
}
