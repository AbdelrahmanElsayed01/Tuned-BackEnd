package com.example.tuned.Domain.Song;

import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class GetSongsResponse {
    private List<Song> Songs;
}
