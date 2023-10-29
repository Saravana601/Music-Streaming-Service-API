package com.geekster.MusicStreamingApp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlaylistSongRequestDto {
    private Long userId;
    private Long playlistId;
    private Long songId;
    private String email;
    private String token;
}
