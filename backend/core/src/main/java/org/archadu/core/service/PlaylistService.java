package org.archadu.core.service;

import org.archadu.core.model.User;
import org.archadu.core.model.Playlist;
import org.archadu.core.repository.PlaylistsRepo;
import org.archadu.core.repository.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class PlaylistService {
    private static final Logger logger = LoggerFactory.getLogger(PlaylistService.class);
    private final UserRepo userRepo;
    private final PlaylistsRepo playlistsRepo;

    public PlaylistService(UserRepo userRepo, PlaylistsRepo playlistsRepo) {
        this.userRepo = userRepo;
        this.playlistsRepo = playlistsRepo;
    }

    @Transactional
    public Playlist createPlaylist(Long userId, String playlistName, String description, String coverUrl, boolean shared, boolean collaborative) {
        User user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        String playlistId = UUID.randomUUID().toString();
        Playlist playlist = new Playlist(playlistId, user, playlistName, description, coverUrl, shared, collaborative);

        try{
            return playlistsRepo.save(playlist);
        } catch(Exception e){
            logger.error("Failed to create playlist", e);
            throw new IllegalArgumentException("Playlist already exists");
        }
    }


    @Transactional
    public Playlist updatePlaylist(String playlistId, Long userId, String playlistName, String description, String coverUrl, Boolean shared, Boolean collaborative) {
        Playlist playlist = playlistsRepo.findById(playlistId);
        if (playlist == null) {
            throw new RuntimeException("Playlist not found");
        } else if(playlist.getDeletedAt() != null) {
            throw new RuntimeException("Playlist is deleted");
        }

        if (userId != null) {
            User user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
            playlist.setUser(user);
        }
        if (playlistName != null) {
            playlist.setPlaylistName(playlistName);
        }
        if (description != null) {
            playlist.setDescription(description);
        }
        if(coverUrl != null) {
            playlist.setCoverUrl(coverUrl);
        }
        if (shared != null) {
            playlist.setShared(shared);
        }
        if (collaborative != null) {
            playlist.setCollaborative(collaborative);
        }
        try {
            return playlistsRepo.save(playlist);
        } catch (Exception e) {
            throw new IllegalArgumentException("Playlist already exists");
        }
    }
    @Transactional
    public void deletePlaylist(String playlistId) {
        Playlist playlist = playlistsRepo.findById(playlistId);
        if (playlist == null) {
            throw new RuntimeException("Playlist not found");
        }
        try{
            playlist.setDeletedAt(LocalDateTime.now());
            playlistsRepo.save(playlist);
        } catch (Exception e){
            throw new IllegalArgumentException("Playlist deletion failed");
        }
    }

    public Playlist getPlaylist(String playlistId) {
        Playlist playlist = playlistsRepo.findById(playlistId);
        if (playlist == null) {
            throw new RuntimeException("Playlist not found");
        }
        return playlist;
    }

}
