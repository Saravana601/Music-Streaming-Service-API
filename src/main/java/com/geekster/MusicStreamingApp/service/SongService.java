package com.geekster.MusicStreamingApp.service;

import com.geekster.MusicStreamingApp.model.Song;
import com.geekster.MusicStreamingApp.model.User;
import com.geekster.MusicStreamingApp.model.enums.UserRole;
import com.geekster.MusicStreamingApp.repository.ISongRepository;
import com.geekster.MusicStreamingApp.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class SongService {

    @Autowired
    ISongRepository songRepository;

    @Autowired
    UserAuthenticationService userAuthenticationService;

    @Autowired
    IUserRepository userRepository;

    /*                 Admin Services                */

    // Method for adding a song to the repository
    public String createSong(String email, String tokenValue, Song song) {
        // Authenticate user email and token
        if(!userAuthenticationService.authenticate(email,tokenValue)) {
            return "Unauthorized access"; // Returns an error message if authentication fails
        }

        // Find the user by email
        User existingUser = userRepository.findFirstByEmail(email);

        // Check the user has admin role
        if(existingUser.getUserRole() != UserRole.ADMIN_ROLE) {
            return "Only admin can able to add songs"; // returns message to non-admin user
        }

        // If user is authenticated and has admin role add song to the repository
        songRepository.save(song);
        return "Song added";
    }

    // Method for adding a songs to the repository
    public String createSongs(String email, String tokenValue, List<Song> songs) {
        // Authenticate user email and token
        if(!userAuthenticationService.authenticate(email,tokenValue)) {
            return "Unauthorized access"; // Returns an error message if authentication fails
        }

        // Find the user by email
        User existingUser = userRepository.findFirstByEmail(email);

        // Check the user has admin role
        if(existingUser.getUserRole() != UserRole.ADMIN_ROLE) {
            return "Only admin can able to add songs"; // returns message to non-admin user
        }

        // If user is authenticated and has admin role add songs to the repository
        songRepository.saveAll(songs);
        return "Songs added";
    }


    // Method for getting songs by song id
    public Song getSongById(String email, String tokenValue, Long songId) {
        // Authenticate user email and token
        if(!userAuthenticationService.authenticate(email,tokenValue)) {
            throw new RuntimeException("Unauthorized access");
        }
        // Find the user by email
        User existingUser = userRepository.findFirstByEmail(email);

        // Check the user has admin role
        if(existingUser.getUserRole() != UserRole.ADMIN_ROLE) {
            throw new RuntimeException("Only admin can able to add songs"); // returns message to non-admin user
        }

        return songRepository.findById(songId).orElseThrow();
    }

    // Method for getting all songs
    public List<Song> getAllSongs(String email, String tokenValue) {
        // Authenticate user email and token
        if(!userAuthenticationService.authenticate(email,tokenValue)) {
            throw new RuntimeException("Unauthorized access");
        }
        // Find the user by email
        User existingUser = userRepository.findFirstByEmail(email);

        // Check the user has admin role
        if(existingUser.getUserRole() != UserRole.ADMIN_ROLE) {
            throw new RuntimeException("Only admin can able to add songs"); // returns message to non-admin user
        }

        return songRepository.findAll();
    }

    // Method for update song
    public String updateSong(String email, String tokenValue, Long songId, Song updatedSong) {
        // Authenticate user email and token
        if(!userAuthenticationService.authenticate(email,tokenValue)) {
            return "Unauthorized access"; // Returns an error message if authentication fails
        }

        // Find the user by email
        User existingUser = userRepository.findFirstByEmail(email);

        // Check the user has admin role
        if(existingUser.getUserRole() != UserRole.ADMIN_ROLE) {
            return "Only admin can able to add songs"; // returns message to non-admin user
        }

        Song existingSong = songRepository.findById(songId).orElseThrow();
        existingSong.setAlbum(updatedSong.getAlbum());
        existingSong.setSongDurationInSeconds(updatedSong.getSongDurationInSeconds());
        existingSong.setName(updatedSong.getName());
        existingSong.setGenre(updatedSong.getGenre());
        existingSong.setArtist(updatedSong.getArtist());
        existingSong.setPlaylists(updatedSong.getPlaylists());

        songRepository.save(existingSong);
        return "Sng updated successfully";
    }



    // Method for delete song by using song id
    public String deleteSongById(String email, String tokenValue, Long songId) {
        // Authenticate user email and token
        if(!userAuthenticationService.authenticate(email,tokenValue)) {
            return "Unauthorized access"; // Returns an error message if authentication fails
        }

        // Find the user by email
        User existingUser = userRepository.findFirstByEmail(email);

        // Check the user has admin role
        if(existingUser.getUserRole() != UserRole.ADMIN_ROLE) {
            return "Only admin can able to add songs"; // returns message to non-admin user
        }

        songRepository.deleteById(songId);
        return "Song deleted successfully";
    }
}
