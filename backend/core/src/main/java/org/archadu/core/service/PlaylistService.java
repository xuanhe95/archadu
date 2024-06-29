package org.archadu.core.service;

import org.archadu.core.model.User;
import org.archadu.core.model.Playlist;
import org.archadu.core.repository.PlaylistsRepo;
import org.archadu.core.repository.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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
    public Playlist createPlaylist(Long userId, String playlistName, String description, String coverUrl, boolean isPublic, boolean isCollaborative) {
        User user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        Playlist existingPlaylist = playlistsRepo.findByPlaylistNameAndUser(playlistName, user);
        if(existingPlaylist != null) {
            throw new IllegalArgumentException("Playlist already exists");
        }

        Playlist playlist = new Playlist(user, playlistName, description, coverUrl, isPublic, isCollaborative);

        try{
            return playlistsRepo.save(playlist);
        } catch(Exception e){
            logger.error("Failed to create playlist", e);
            throw new IllegalArgumentException("Playlist already exists");
        }
    }


@Transactional
    public Playlist updatePlaylist(String playlistId, Long userId, String playlistName, String description, String coverUrl, Boolean isPublic, Boolean isCollaborative) {
        Playlist playlist = playlistsRepo.findById(playlistId);
        if (playlist == null) {
            throw new RuntimeException("Playlist not found");
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
        if (isPublic != null) {
            playlist.setPublic(isPublic);
        }
        if (isCollaborative != null) {
            playlist.setCollaborative(isCollaborative);
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
            playlistsRepo.delete(playlist);
        } catch (Exception e){
            throw new IllegalArgumentException("Playlist not found");
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
