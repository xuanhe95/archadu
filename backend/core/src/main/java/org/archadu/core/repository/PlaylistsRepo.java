package org.archadu.core.repository;

import org.archadu.core.model.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaylistsRepo extends JpaRepository<Playlist, Long> {

    Playlist findById(String playlistId);

}
