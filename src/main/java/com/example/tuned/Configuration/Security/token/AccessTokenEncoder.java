package com.example.tuned.Configuration.Security.token;

public interface AccessTokenEncoder {
    String encode(AccessToken accessToken);
}
