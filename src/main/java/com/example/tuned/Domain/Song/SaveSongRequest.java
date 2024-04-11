package com.example.tuned.Domain.Song;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SaveSongRequest {
    private String title;
    private String artist;
    private String uri;
    private String image;
    private String duration;
}
