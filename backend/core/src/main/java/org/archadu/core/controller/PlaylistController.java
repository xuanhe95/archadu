package org.archadu.core.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import org.archadu.core.dto.PlaylistRequest;
import org.archadu.core.dto.Response;
import org.archadu.core.model.Playlist;
import org.archadu.core.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@RestController
@RequestMapping("/api/playlist")
public class PlaylistController {

    private static final Logger log = LoggerFactory.getLogger(PlaylistController.class);
    private final PlaylistService playlistService;

    @Autowired
    public PlaylistController(PlaylistService playlistService) {
        log.info("PlaylistController created");
        this.playlistService = playlistService;
    }

    @SaCheckLogin
    @PostMapping("/playlists")
    public Response<Playlist> createPlaylist(@RequestBody  PlaylistRequest req) {
        log.info("Create playlist request received");
        Playlist playlist = playlistService.createPlaylist(req.userId(), req.playlistName(), req.description(), req.coverUrl(), req.shared(), req.collaborative());
        return Response.success(playlist);
    }

    @DeleteMapping("/playlists")
    public Response<String> deletePlaylist(@RequestParam String id) {
        try{
            playlistService.deletePlaylist(id);
            return Response.success("Delete playlist success");
        } catch (Exception e) {
            return Response.error("Delete playlist failed");
        }
    }

    @PutMapping("/playlists")
    public Response<Playlist> updatePlaylist(@RequestBody PlaylistRequest req) {
        Playlist playlist = playlistService.updatePlaylist(req.playlistId(), req.userId(), req.playlistName(), req.description(), req.coverUrl(), req.shared(), req.collaborative());
        return Response.success(playlist);
    }

}
