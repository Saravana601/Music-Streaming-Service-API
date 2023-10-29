package com.geekster.MusicStreamingApp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.geekster.MusicStreamingApp.model.enums.SongGenre;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long songId;

    @NotBlank(message = "Song name is mandatory")
    private String name;

    @NotBlank(message = "Artist name is mandatory")
    private String artist;

    @NotBlank(message = "Album name is mandatory")
    private String album;

    @NotNull(message = "Please enter a song duration in seconds")
    private Integer songDurationInSeconds;

    @Enumerated(EnumType.STRING)
    private SongGenre genre;

    @JsonIgnore
    @ManyToMany(mappedBy = "songs")
    private List<Playlist> playlists;
}
