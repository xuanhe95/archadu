package org.archadu.core.service;
import org.archadu.core.fm.ArtistRepo;
import org.archadu.core.fm.model.Area;
import org.archadu.core.fm.model.Artist;
import org.archadu.core.fm.model.ArtistType;
import org.archadu.core.fm.model.Gender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class ArtistService {

    private final ArtistRepo artistRepo;
    @Autowired
    public ArtistService(ArtistRepo artistRepo) {
        this.artistRepo = artistRepo;
    }



}

