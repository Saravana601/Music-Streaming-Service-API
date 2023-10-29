package com.geekster.MusicStreamingApp.repository;

import com.geekster.MusicStreamingApp.model.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPlaylistRepository extends JpaRepository<Playlist,Long> {
}
