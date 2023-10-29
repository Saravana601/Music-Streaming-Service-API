package com.geekster.MusicStreamingApp.controller;

import com.geekster.MusicStreamingApp.model.Song;
import com.geekster.MusicStreamingApp.model.User;
import com.geekster.MusicStreamingApp.model.dto.AuthenticationDto;
import com.geekster.MusicStreamingApp.model.dto.SignInInput;
import com.geekster.MusicStreamingApp.service.SongService;
import com.geekster.MusicStreamingApp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    SongService songService;

    @Autowired
    UserService userService;

    @PostMapping("/users/sign-up")
    public String signUp(@RequestBody @Valid User user) {
        return userService.userSignUp(user);
    }

    @PostMapping("/users/sign-in")
    public String signIn(@RequestBody SignInInput sign) {
        return userService.userSignIn(sign);
    }

    @DeleteMapping("/users/sign-out")
    public String signOut(@RequestBody AuthenticationDto authenticationDto) {
        return userService.userSignOut(authenticationDto.getEmail(), authenticationDto.getTokenValue());
    }

    // Song Management Endpoints


    @PostMapping("/songs")
    public String createSong(@RequestParam String email, @RequestParam String token, @RequestBody @Valid Song song) {
        return songService.createSong(email, token, song);
    }

    @PostMapping("/songs/batch")
    public String createSongs(@RequestParam String email, @RequestParam String token, @RequestBody List<@Valid Song> songs) {
        return songService.createSongs(email, token, songs);
    }

    @GetMapping("/songs/{songId}")
    public Song getSongById(@RequestParam String email, @RequestParam String token, @PathVariable Long songId) {
        return songService.getSongById(email, token, songId);
    }

    @GetMapping("/songs")
    public List<Song> getAllSongs(@RequestParam String email, @RequestParam String token) {
        return songService.getAllSongs(email, token);
    }

    @PutMapping("/songs/{songId}")
    public String updateSong(@RequestParam String email, @RequestParam String token, @PathVariable Long songId, @RequestBody @Valid Song updatedSong) {
        return songService.updateSong(email, token, songId, updatedSong);
    }

    @DeleteMapping("/songs/{songId}")
    public String deleteSongById(@RequestParam String email, @RequestParam String token, @PathVariable Long songId) {
        return songService.deleteSongById(email, token, songId);
    }
}