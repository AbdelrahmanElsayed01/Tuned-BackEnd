package com.example.tuned.Business.Impl.UserUseCaseImpl;

import com.example.tuned.Business.Impl.PlaylistUseCaseImpl.PlaylistConverter;
import com.example.tuned.Domain.User.User;
import com.example.tuned.Persistence.Entity.UserEntity;

public class UserConvertor {

    private UserConvertor(){

    }

    public static User convert(UserEntity user){
        return User.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .playlist(PlaylistConverter.convert(user.getPlaylist()))
                .subscription(user.getSubscription())
                .build();
    }
}
