package com.example.tuned.Domain.User;

import com.example.tuned.Business.enums.SubscriptionType;
import com.example.tuned.Domain.Playlist.Playlist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long userId;
    private String name;
    private String username;
    private String email;
    private String password;
    private SubscriptionType subscription;
    private Playlist playlist;

}

