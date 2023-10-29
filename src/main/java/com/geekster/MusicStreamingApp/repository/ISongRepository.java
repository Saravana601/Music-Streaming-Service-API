package com.geekster.MusicStreamingApp.repository;

import com.geekster.MusicStreamingApp.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISongRepository extends JpaRepository<Song,Long> {
}
