package org.archadu.core.controller;

import org.archadu.core.fm.model.Track;
import org.archadu.core.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/track")
public class TrackController {

    private final TrackService trackService;
    @Autowired
    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }

    @GetMapping("/get")
    public String getTrack(@RequestParam UUID gid) {
        Track track = trackService.getTrackByGid(UUID.randomUUID());
        if(track == null) {
            return "Track not found";
        }
        return track.toString();
    }


}
