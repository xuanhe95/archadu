package org.archadu.core.repository;

import org.archadu.core.model.Playlist;
import org.archadu.core.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaylistsRepo extends JpaRepository<Playlist, Long> {

    Playlist findById(String playlistId);

    Playlist findByPlaylistNameAndUser(String playlistName, User user);

}
