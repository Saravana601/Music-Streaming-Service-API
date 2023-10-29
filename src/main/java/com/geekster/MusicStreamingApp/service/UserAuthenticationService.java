package com.geekster.MusicStreamingApp.service;

import com.geekster.MusicStreamingApp.model.UserAuthentication;
import com.geekster.MusicStreamingApp.repository.IUserAuthenticationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAuthenticationService {

    @Autowired
    IUserAuthenticationRepository userAuthenticationRepository;

    // It is used to create a token
    public void createToken(UserAuthentication token) {
        // save the token to database
        userAuthenticationRepository.save(token);
    }

    // This method is used to authenticate the valid user
    public boolean authenticate(String email, String tokenValue) {
        // finding token by using tokenValue
        UserAuthentication token = userAuthenticationRepository.findFirstByTokenValue(tokenValue);

        // returns true if token is not equal to null and email matches
        return token != null && email.equals(token.getUser().getEmail());
    }

    // Method for delete token
    public void deleteToken(String tokenValue) {
        UserAuthentication token = userAuthenticationRepository.findFirstByTokenValue(tokenValue);
        userAuthenticationRepository.delete(token); // deletes token
    }
}
