package com.example.tuned.Business.Impl.UserUseCaseImpl;

import com.example.tuned.Business.Interfaces.UserInterfaces.SwitchSubscriptionUseCase;
import com.example.tuned.Business.enums.SubscriptionType;
import com.example.tuned.Persistence.Entity.PlaylistEntity;
import com.example.tuned.Persistence.Entity.UserEntity;
import com.example.tuned.Persistence.PlaylistRepository;
import com.example.tuned.Persistence.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SwitchSubscriptionUseCaseImpl implements SwitchSubscriptionUseCase {

    private final UserRepository userRepository;
    private final PlaylistRepository playlistRepository;
    @Override
    public void GoPremium(Long userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        if (user.getSubscription() == SubscriptionType.free) {
            user.setSubscription(SubscriptionType.premium);
            PlaylistEntity playlist = PlaylistEntity.builder()
                    .user(user)
                    .build();

            user.setPlaylist(playlist);
            userRepository.save(user);
        }
    }
}
