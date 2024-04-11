package com.example.tuned.Configuration.Security.token;

public interface AccessTokenDecoder {
    AccessToken decode(String accessTokenEncoded);
}
