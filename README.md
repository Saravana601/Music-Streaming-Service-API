# <p align ="center">  Music-Streaming-Service </p>

<p align="center">
<a href="Java url">
    <img alt="Java" src="https://img.shields.io/badge/Java-17-purple.svg" />
</a>
<a href="Maven url" >
    <img alt="Maven" src="https://img.shields.io/badge/maven-3.0.5-blue.svg" />
</a>
<a href="Spring Boot url" >
    <img alt="Spring Boot" src="https://img.shields.io/badge/Spring Boot-3.1.5-yellow.svg" />
</p>

## Overview
The Music Streaming App is a web application designed to provide music enthusiasts with a platform to explore and enjoy music. It offers a range of features for both users and administrators, ensuring a comprehensive music streaming experience.

## Frameworks And Languages:
The Music Streaming Service project is developed using the following frameworks and languages:

- Spring Boot: A Java-based framework for building web applications.
- Spring MVC: A module of the Spring Framework that supports building web applications.
- Java: The programming language used for backend development.
- Hibernate: An Object-Relational Mapping (ORM) framework used for database interactions.
- MySQL: The chosen database management system.

## Dependencies Used :
The dependencies used in the pom.xml file for this project are :

- Spring Starter Web: Provides essential web-related features and configurations.
- Spring JPA: Simplifies working with relational databases using Java Persistence API (JPA).
- Lombok: Reduces boilerplate code with annotations for getter, setter, and other common methods.
- Validation: Enables data validation using annotations.
- MySQL Driver: The chosen database management system for data storage.
- Swagger: To generate interactive API (documentation).

## Features

### User Management

1. **Sign Up:**
   - Users can register for an account with their information, including email and password.
   - It validates admin email addresses ending with "admin.com."

2. **Sign In:**
   - Registered users can sign in with their email and password, generating a unique token for authentication.

3. **Sign Out:**
   - Users can log out, which invalidates their token for security.

### Playlist Management

1. **Create Playlist:**
   - Normal users can create playlists with unique names.
   - Admins have additional privileges.

2. **Update Playlist Name:**
   - Users can change the name of their playlists.

3. **Delete Playlist:**
   - Users can remove their playlists.

### Playlist-Song Management

1. **Add Song to Playlist:**
   - Users can add songs to their playlists.
   - The system checks for permissions and avoids duplicate entries.

2. **Remove Song from Playlist:**
   - Users can remove songs from their playlists.

3. **Get Playlist Songs:**
   - Users can view the songs in their playlists (limited to 10 at a time).

### Admin Functions

1. **Song Management:**
   - Admin users have control over adding, updating, and deleting songs in the system.
   - They can add single songs or in batches.

### Security

- The application employs a token-based authentication system to ensure user and data security.


## Data Flow

### Controller

#### AdminController

- `/admin/users/sign-up`: POST - Register a user.
- `/admin/users/sign-in`: POST - Authenticate and sign in a user.
- `/admin/users/sign-out`: DELETE - Sign out a user.
- `/admin/songs`: POST - Create a song (Admin-only).
- `/admin/songs/batch`: POST - Create multiple songs (Admin-only).
- `/admin/songs/{songId}`: GET - Get a song by ID (Admin-only).
- `/admin/songs`: GET - Get all songs (Admin-only).
- `/admin/songs/{songId}`: PUT - Update a song (Admin-only).
- `/admin/songs/{songId}`: DELETE - Delete a song (Admin-only).

#### UserController

- `/users/sign_up`: POST - Register a user.
- `/users/sign_in`: POST - Authenticate and sign in a user.
- `/users/sign_out`: DELETE - Sign out a user.
- `/playlists`: POST - Create a playlist.
- `/playlists`: GET - Get user's playlists.
- `/playlists`: PUT - Update a playlist name.
- `/playlists`: DELETE - Delete a playlist.
- `/playlists/songs`: POST - Add a song to a playlist.
- `/playlists/songs`: GET - Get songs from a playlist.
- `/playlists/songs`: DELETE - Remove a song from a playlist.

### Services

- **UserService**: Manages user-related operations like sign-up, sign-in, and sign-out.
- **PlaylistService**: Handles playlist-related actions, including creating, updating, and deleting playlists, and managing songs within playlists.
- **SongService**: Manages song-related functions, including adding, updating, and deleting songs.
- **UserAuthenticationService**: Responsible for token-based user authentication.

### Repository

- **IUserRepository**: A repository for managing user data.
- **IPlaylistRepository**: Manages playlist data.
- **ISongRepository**: Manages song data.
- **IUserAuthenticationRepository**: Manages user authentication tokens.

## Database Design:

The project uses MySQL as the database management system. To design the database for the music streaming app, we need to consider the entities (tables) and their relationships. Based on the features and models mentioned in this file.
    
### Relationships:

- Each User can have multiple Playlists.
- Each Playlist can have multiple Songs.
- Each User can have multiple Songs in their playlists, and each Song can be part of multiple Playlists.

## Data Structure:

The project utilizes several data structures, including:

* Strings: Used for storing text-based data, such as user names, email addresses, phone numbers, artist names, album names, song names and authentication tokens.
* Integers: Used for storing numerical data, such as IDs.
* LocalDate: Used for storing date information, including token creation dates.
* ArrayList: To organize and manage data efficiently, such as lists of songs, and playlists.

## Deployment using AWS
Once developed and tested the music streaming app locally, next deployed it to a cloud platform like AWS (Amazon Web Services) to make it accessible to users worldwide. AWS provides a robust and scalable infrastructure to host web applications, making it a popular choice for deploying Spring Boot applications.

**Deployment Link** : [http://65.2.150.67:8080/swagger-ui/index.html#/](http://65.2.150.67:8080/swagger-ui/index.html#/)

## Project Summary:
The music streaming app aims to provide users with a seamless experience to explore, discover, and enjoy their favourite music. It leverages the Spring Boot framework and related dependencies for efficient web development and data management. Users can register, log in, and create personalized accounts, allowing them to access a vast library of songs, and create playlists. And deployed the project using AWS(Amazon Web Service).

## Contributing

Contributions are welcome! Feel free to open issues or pull requests for any improvements or bug fixes.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Contact

If you have any questions or suggestions, feel free to contact me at the following
- [Gmail](saravanad2401@gmail.com).
- [LinkedIn](https://www.linkedin.com/in/saravanad2401/).
