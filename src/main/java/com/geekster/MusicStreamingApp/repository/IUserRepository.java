package com.geekster.MusicStreamingApp.repository;

import com.geekster.MusicStreamingApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User,Long> {
    User findFirstByEmail(String email);
}
