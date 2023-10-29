package com.geekster.MusicStreamingApp.service;

import com.geekster.MusicStreamingApp.model.User;
import com.geekster.MusicStreamingApp.model.UserAuthentication;
import com.geekster.MusicStreamingApp.model.dto.SignInInput;
import com.geekster.MusicStreamingApp.model.enums.UserRole;
import com.geekster.MusicStreamingApp.repository.IUserRepository;
import com.geekster.MusicStreamingApp.service.utility.EmailHandler;
import com.geekster.MusicStreamingApp.service.utility.PasswordEncrypter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
public class UserService {

    @Autowired
    IUserRepository userRepository;

    @Autowired
    UserAuthenticationService userAuthenticationService;

    // User sign up
    public String userSignUp(User user) {
        if(user.getUserRole() == UserRole.ADMIN_ROLE) {
            // Checks email ends with admin.com
            if(!user.getEmail().endsWith("admin.com")) {
                return "Admin email must end with 'admin.com'";
            }
        }

        // Checks if user is already signed up or not?
        User existingUser = userRepository.findFirstByEmail(user.getEmail());

        if(existingUser != null) {
            return "You're already signed up! Please sign in";
        }

        // unhashed user password
        String password = user.getPassword();

        try {
            // encrypting user password using MD5 algorithm
            String encryptedPassword = PasswordEncrypter.encrypt(password);

            // save the encrypted password to user data
            user.setPassword(encryptedPassword);
            userRepository.save(user);

            return "Signed up successfully. Your id is " + user.getUserId();
        }
        catch (NoSuchAlgorithmException e) {
            return "Internal server error. Please try again later";
        }
    }

    // user sign in
    public String userSignIn(SignInInput sign) {
        // Checks if user is signed up or not?
        User existingUser = userRepository.findFirstByEmail(sign.getEmail());

        if(existingUser == null) {
            return "Please sign up first";
        }

        // Check if user is already signed in
        if (existingUser.isSignedIn()) {
            return "You're already signed in.";
        }


        String password = sign.getPassword();

        try {
            String encryptedPassword = PasswordEncrypter.encrypt(password);
            // checks the password matches
            if(encryptedPassword.equals(existingUser.getPassword())) {
                UserAuthentication token = new UserAuthentication(existingUser);

                // creating token
                userAuthenticationService.createToken(token);

                // Update sign-in status
                existingUser.setSignedIn(true);
                userRepository.save(existingUser);

                // Sending token to user mail
                EmailHandler.sendMail(existingUser.getEmail(),"Token for future use", token.getTokenValue());
                return "Token value - "+ token.getTokenValue() +". Also token sent to your email";
            }
            else {
                return "User credentials are incorrect";
            }
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    // user - sign - out
    public String userSignOut(String email, String tokenValue) {
        User existingUser = userRepository.findFirstByEmail(email);
        // authenticates valid user
        if(userAuthenticationService.authenticate(email,tokenValue)) {

            // update the sign-in status
            existingUser.setSignedIn(false);
            userRepository.save(existingUser);

            userAuthenticationService.deleteToken(tokenValue);
            return "Signed out successfully";
        }
        return "Unauthorized access";
    }
}