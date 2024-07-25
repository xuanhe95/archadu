package org.archadu.core.service;

import org.archadu.core.fm.TrackRepo;
import org.archadu.core.fm.model.Track;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TrackService {


    @Autowired
    private TrackRepo trackRepo;

    public Track getTrackByGid(UUID gid) {
        return trackRepo.findByGid(gid);
    }



}
