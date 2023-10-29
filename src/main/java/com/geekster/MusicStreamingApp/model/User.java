package com.geekster.MusicStreamingApp.model;

import com.geekster.MusicStreamingApp.model.enums.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @NotBlank(message = "Please enter the username")
    private String name;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @Email(message = "Please enter a valid email")
    private String email;

    private String password;

    @NotEmpty
    @Size(min=10,max=12)
    @Pattern(regexp = "^[0-9]+$",message = "Phone number must contain 10 digit")
    private String phoneNumber;

    @Column(name = "signed_in")
    private boolean signedIn;
}

