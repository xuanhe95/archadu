package org.archadu.core.controller;

import org.archadu.core.dto.PlaylistRequest;
import org.archadu.core.dto.Response;
import org.archadu.core.model.Playlist;
import org.archadu.core.repository.UserRepo;
import org.archadu.core.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@RestController
@RequestMapping("/api/playlist")
public class PlaylistController {

    private static final Logger logger = LoggerFactory.getLogger(PlaylistController.class);

    private final PlaylistService playlistService;
    @Autowired
    public PlaylistController(PlaylistService playlistService) {

        logger.info("PlaylistController created");
        this.playlistService = playlistService;
    }

    @PostMapping("/playlists")
    public Response<Playlist> createPlaylist(@RequestBody  PlaylistRequest req) {
        logger.info("Create playlist request received");
        Playlist playlist = playlistService.createPlaylist(req.userId(), req.playlistName(), req.description(), req.coverUrl(), req.shared(), req.collaborative());
        return new Response<Playlist>("Create new playlist success", playlist);
    }

    @DeleteMapping("/playlists")
    public Response<Playlist> deletePlaylist(@RequestParam String id) {
        try{
            playlistService.deletePlaylist(id);
            return new Response<Playlist>("Delete playlist success", null);
        } catch (Exception e) {
            return new Response<Playlist>("Delete playlist failed", null);
        }
    }

    @PutMapping("/playlists")
    public Response<Playlist> updatePlaylist(@RequestBody PlaylistRequest req) {
        Playlist playlist = playlistService.updatePlaylist(req.playlistId(), req.userId(), req.playlistName(), req.description(), req.coverUrl(), req.shared(), req.collaborative());
        return new Response<Playlist>("Update playlist success", playlist);
    }

}
