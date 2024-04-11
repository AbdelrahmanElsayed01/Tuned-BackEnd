package com.example.tuned.Domain.Song;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Song {
    private Long id;
    private String title;
    private String artist;
    private String uri;
    private String image;
    private String duration;
}
