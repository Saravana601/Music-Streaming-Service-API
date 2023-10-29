package com.geekster.MusicStreamingApp.repository;

import com.geekster.MusicStreamingApp.model.UserAuthentication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserAuthenticationRepository extends JpaRepository<UserAuthentication,Long> {
    UserAuthentication findFirstByTokenValue(String tokenValue);
}
