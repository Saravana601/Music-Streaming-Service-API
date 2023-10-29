package com.geekster.MusicStreamingApp.service;

import com.geekster.MusicStreamingApp.model.Playlist;
import com.geekster.MusicStreamingApp.model.Song;
import com.geekster.MusicStreamingApp.model.User;
import com.geekster.MusicStreamingApp.model.dto.PlaylistRequestDto;
import com.geekster.MusicStreamingApp.model.dto.PlaylistSongRequestDto;
import com.geekster.MusicStreamingApp.model.enums.UserRole;
import com.geekster.MusicStreamingApp.repository.IPlaylistRepository;
import com.geekster.MusicStreamingApp.repository.ISongRepository;
import com.geekster.MusicStreamingApp.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlaylistService {

    @Autowired
    IPlaylistRepository playlistRepository;

    @Autowired
    UserAuthenticationService userAuthenticationService;

    @Autowired
    IUserRepository userRepository;

    @Autowired
    ISongRepository songRepository;

    /*  ---  User Service ---  */

    // Method for creating a playlist
    public String createPlaylist(Long userId, String playlistName , String email, String tokenValue) {
        // Authenticates valid user
        if(!userAuthenticationService.authenticate(email,tokenValue)) {
            return "UnAuthorized access"; // returns message if authentication fails
        }

        // Finding user by user id
        User user = userRepository.findById(userId)
                .orElseThrow(); // throw exception if user id is not found

        // Checks the role of user
        if(user.getUserRole() != UserRole.NORMAL_ROLE) {
            return "Only Normal user's can create playlist"; // returns message to other user
        }

        // creates a playlist
        Playlist playlist = new Playlist();
        playlist.setName(playlistName);
        playlist.setUser(user);

        // save playlist to database
        playlistRepository.save(playlist);

        return "Playlist Created";
    }

    // Method for getting playlists
    public List<Playlist> getPlaylists(Long userId, String email, String tokenValue) {
        // Authenticates valid user
        if(!userAuthenticationService.authenticate(email,tokenValue)) {
            throw new RuntimeException("UnAuthorized access"); // returns message if authentication fails
        }

        // Finding user by user id
        User user = userRepository.findById(userId)
                .orElseThrow(); // throw exception if user id is not found

        // Checks the role of user
        if(user.getUserRole() != UserRole.NORMAL_ROLE) {
            throw new RuntimeException("Only Normal user's can create playlist"); // returns message to other user
        }

        return playlistRepository.findAll();
    }

    // Method for updating playlist name
    public String updatePlaylistName(String playlistName, PlaylistRequestDto playlistRequestDto) {
        // Extract fields from dto
        Long userId = playlistRequestDto.getUserId();
        Long playlistId = playlistRequestDto.getPlaylistId();
        String email = playlistRequestDto.getEmail();
        String tokenValue = playlistRequestDto.getToken();

        // Authenticating valid user
        if(!userAuthenticationService.authenticate(email,tokenValue)) {
            return "UnAuthorized access"; // returns message if authentication fails
        }
        // Finding user by their id
        User user = userRepository.findById(userId)
                .orElseThrow(); // throw exception if user is not found

        // Checks the role of user
        if(user.getUserRole() != UserRole.NORMAL_ROLE) {
            return "Only Normal user's can delete playlist"; // returns message to other user
        }

        // Finding playlist by id
        Playlist playlist = playlistRepository.findById(playlistId)
                .orElseThrow(); // throw exception if playlist is not found

        // Checks the playlist is his playlist
        if (!playlist.getUser().equals(user)) {
            return "Unauthorized: You don't have permission to delete this playlist";
        }

        playlist.setName(playlistName);
        playlistRepository.save(playlist);
        return "Playlist name updated successfully";
    }

    // Method for delete a playlist
    public String deletePlaylist(PlaylistRequestDto playlistRequestDto) {
        // Extract fields from dto
        Long userId = playlistRequestDto.getUserId();
        Long playlistId = playlistRequestDto.getPlaylistId();
        String email = playlistRequestDto.getEmail();
        String tokenValue = playlistRequestDto.getToken();

        // Authenticating valid user
        if(!userAuthenticationService.authenticate(email,tokenValue)) {
            return "UnAuthorized access"; // returns message if authentication fails
        }
        // Finding user by their id
        User user = userRepository.findById(userId)
                .orElseThrow(); // throw exception if user is not found

        // Checks the role of user
        if(user.getUserRole() != UserRole.NORMAL_ROLE) {
            return "Only Normal user's can delete playlist"; // returns message to other user
        }

        // Finding playlist by id
        Playlist playlist = playlistRepository.findById(playlistId)
                .orElseThrow(); // throw exception if playlist is not found

        // Checks the playlist is his playlist
        if (!playlist.getUser().equals(user)) {
            return "Unauthorized: You don't have permission to delete this playlist";
        }

        // delete the playlist
        playlistRepository.delete(playlist);
        return "Playlist deleted successfully";
    }

    /* ---    playlist - song service --- */

    // Method for adding a song to playlist
    public String addSongToPlaylist(PlaylistSongRequestDto playlistSongRequestDto) {
        Long userId = playlistSongRequestDto.getUserId();
        Long playlistId = playlistSongRequestDto.getPlaylistId();
        Long songId = playlistSongRequestDto.getSongId();
        String email = playlistSongRequestDto.getEmail();
        String tokenValue = playlistSongRequestDto.getToken();

        // Authenticating valid user
        if(!userAuthenticationService.authenticate(email,tokenValue)) {
            return "UnAuthorized access"; // returns message if authentication fails
        }
        // Finding user by their id
        User user = userRepository.findById(userId)
                .orElseThrow(); // throw exception if user is not found

        // Checks the role of user
        if(user.getUserRole() != UserRole.NORMAL_ROLE) {
            return "Only Normal user's can delete playlist"; // returns message to other user
        }

        // Finding playlist by id
        Playlist playlist = playlistRepository.findById(playlistId)
                .orElseThrow(); // throw exception if playlist is not found

        // Checks the playlist is his playlist
        if (!playlist.getUser().equals(user)) {
            return "Unauthorized: You don't have permission to delete this playlist";
        }

        Song song = songRepository.findById(songId)
                .orElseThrow(); // throw exception if song is not found

        // Get all songs in corresponding playlist
        List<Song> songs = playlist.getSongs();

        // Checks the song is already in the playlist
        if(songs.contains(song)) {
            return "Song already in playlist";
        }

        // Adding the song to the playlist
        songs.add(song);
        playlist.setSongs(songs);

        // Update the playlist in the database
        playlistRepository.save(playlist);

        return "Song added in playlist";
    }

    public String deleteSongFromPlaylist(PlaylistSongRequestDto playlistSongRequestDto) {
        Long userId = playlistSongRequestDto.getUserId();
        Long playlistId = playlistSongRequestDto.getPlaylistId();
        Long songId = playlistSongRequestDto.getSongId();
        String email = playlistSongRequestDto.getEmail();
        String tokenValue = playlistSongRequestDto.getToken();

        // Authenticating valid user
        if(!userAuthenticationService.authenticate(email,tokenValue)) {
            return "UnAuthorized access"; // returns message if authentication fails
        }
        // Finding user by their id
        User user = userRepository.findById(userId)
                .orElseThrow(); // throw exception if user is not found

        // Checks the role of user
        if(user.getUserRole() != UserRole.NORMAL_ROLE) {
            return "Only Normal user's can delete playlist"; // returns message to other user
        }

        // Finding playlist by id
        Playlist playlist = playlistRepository.findById(playlistId)
                .orElseThrow(); // throw exception if playlist is not found

        // Checks the playlist is his playlist
        if (!playlist.getUser().equals(user)) {
            return "Unauthorized: You don't have permission to delete this playlist";
        }

        // Finding song by id
        Song song = songRepository.findById(songId)
                .orElseThrow(); // throw exception if song is not found


        // remove song from playlist
        playlist.getSongs().remove(song);

        // save playlist to repository
        playlistRepository.save(playlist);
        return "Song deleted successfully";
    }

    // Method for getting songs from playlist - It gets only 10 songs at a time
    public List<Song> getSongsFromPlaylist(Long userId, Long playlistId, String email, String tokenValue) {
        // Authenticating valid user
        if(!userAuthenticationService.authenticate(email,tokenValue)) {
            throw new RuntimeException("UnAuthorized access"); // returns message if authentication fails
        }
        // Finding user by their id
        User user = userRepository.findById(userId)
                .orElseThrow(); // throw exception if user is not found

        // Checks the role of user
        if(user.getUserRole() != UserRole.NORMAL_ROLE) {
            throw new RuntimeException("UnAuthorized access"); // returns message to other user
        }

        // Finding playlist by id
        Playlist playlist = playlistRepository.findById(playlistId)
                .orElseThrow(); // throw exception if playlist is not found

        // Checks the playlist is his playlist
        if (!playlist.getUser().equals(user)) {
            throw new RuntimeException("UnAuthorized access");
        }

        return playlist.getSongs()
                .stream()
                .limit(10)
                .collect(Collectors.toList());
    }
}
