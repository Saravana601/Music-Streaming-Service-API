package com.geekster.MusicStreamingApp.controller;

import com.geekster.MusicStreamingApp.model.Playlist;
import com.geekster.MusicStreamingApp.model.Song;
import com.geekster.MusicStreamingApp.model.User;
import com.geekster.MusicStreamingApp.model.dto.AuthenticationDto;
import com.geekster.MusicStreamingApp.model.dto.PlaylistRequestDto;
import com.geekster.MusicStreamingApp.model.dto.PlaylistSongRequestDto;
import com.geekster.MusicStreamingApp.model.dto.SignInInput;
import com.geekster.MusicStreamingApp.service.PlaylistService;
import com.geekster.MusicStreamingApp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
public class UserController {

    @Autowired
    PlaylistService playlistService;

    @Autowired
    UserService userService;

    // User Management Endpoint

    @PostMapping("/users/sign_up")
    public String signUp(@RequestBody @Valid User user) {
        return userService.userSignUp(user);
    }

    @PostMapping("/users/sign_in")
    public String signIn(@RequestBody SignInInput sign) {
        return userService.userSignIn(sign);
    }

    @DeleteMapping("/users/sign_out")
    public String signOut(@RequestBody AuthenticationDto authenticationDto) {
        return userService.userSignOut(authenticationDto.getEmail(), authenticationDto.getTokenValue());
    }

    // Playlist Management Endpoints

    @PostMapping("/playlists")
    public String createPlaylist(@RequestParam Long userId, @RequestParam String playlistName, @RequestParam String email, @RequestParam String token) {
        return playlistService.createPlaylist(userId, playlistName, email, token);
    }

    @GetMapping("/playlists")
    public List<Playlist> getPlaylists(@RequestParam Long userId, @RequestParam String email, @RequestParam String token) {
        return playlistService.getPlaylists(userId, email, token);
    }

    @PutMapping("/playlists")
    public String updatePlaylistName(@RequestParam String playlistName, @RequestBody PlaylistRequestDto playlistRequestDto) {
        return playlistService.updatePlaylistName(playlistName, playlistRequestDto);
    }

    @DeleteMapping("/playlists")
    public String deletePlaylist(@RequestBody PlaylistRequestDto playlistRequestDto) {
        return playlistService.deletePlaylist(playlistRequestDto);
    }

    // Playlist-Song Management Endpoints

    @PostMapping("/playlists/songs")
    public String addToPlaylist(@RequestBody PlaylistSongRequestDto playlistSongRequestDto) {
        return playlistService.addSongToPlaylist(playlistSongRequestDto);
    }

    @GetMapping("/playlists/songs")
    public List<Song> getPlaylistSongs(@RequestParam Long userId, @RequestParam Long playlistId, @RequestParam String email, @RequestParam String token){
        return playlistService.getSongsFromPlaylist(userId, playlistId, email, token);
    }

    @DeleteMapping("/playlists/songs")
    public String removeFromPlaylist(@RequestBody PlaylistSongRequestDto playlistSongRequestDto) {
        return playlistService.deleteSongFromPlaylist(playlistSongRequestDto);
    }
}
