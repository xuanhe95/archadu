package org.archadu.core.controller;

import org.archadu.core.dto.PlaylistRequest;
import org.archadu.core.dto.Response;
import org.archadu.core.model.Playlist;
import org.archadu.core.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/playlist")
public class PlaylistController {
    private final PlaylistService playlistService;
    @Autowired
    public PlaylistController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @PostMapping()
    public Response<Playlist> createPlaylist(PlaylistRequest req) {
        Playlist playlist = playlistService.createPlaylist(req.userId(), req.playlistName(), req.description(), req.coverUrl(), req.isPublic(), req.isCollaborative());
        return new Response<Playlist>("Create new playlist success", playlist);
    }

    @DeleteMapping()
    public Response<Playlist> deletePlaylist(PlaylistRequest req) {
        playlistService.deletePlaylist(req.playlistId());
        return new Response<Playlist>("Delete playlist success", null);
    }

    @PutMapping()
    public Response<Playlist> updatePlaylist(PlaylistRequest req) {
        Playlist playlist = playlistService.updatePlaylist(req.playlistId(), req.userId(), req.playlistName(), req.description(), req.coverUrl(), req.isPublic(), req.isCollaborative());
        return new Response<Playlist>("Update playlist success", playlist);
    }

}
