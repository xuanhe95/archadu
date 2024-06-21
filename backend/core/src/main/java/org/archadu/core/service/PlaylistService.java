package org.archadu.core.service;

import org.archadu.core.model.User;
import org.archadu.core.model.Playlist;
import org.archadu.core.repository.PlaylistsRepo;
import org.archadu.core.repository.UserRepo;
import org.springframework.stereotype.Service;

@Service
public class PlaylistService {
    private final UserRepo userRepo;
    private final PlaylistsRepo playlistsRepo;

    public PlaylistService(UserRepo userRepo, PlaylistsRepo playlistsRepo) {
        this.userRepo = userRepo;
        this.playlistsRepo = playlistsRepo;
    }

    public Playlist createPlaylist(Long userId, String playlistName, String description, String coverUrl, boolean isPublic, boolean isCollaborative) {
        User user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Playlist playlist = new Playlist(user, playlistName, description, coverUrl, isPublic, isCollaborative);
        playlistsRepo.save(playlist);
        return playlist;
    }

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
        playlistsRepo.save(playlist);
        return playlist;
    }

    public void deletePlaylist(String playlistId) {
        Playlist playlist = playlistsRepo.findById(playlistId);
        if (playlist == null) {
            throw new RuntimeException("Playlist not found");
        }
        playlistsRepo.delete(playlist);
    }

    public Playlist getPlaylist(String playlistId) {
        Playlist playlist = playlistsRepo.findById(playlistId);
        if (playlist == null) {
            throw new RuntimeException("Playlist not found");
        }
        return playlist;
    }

}
