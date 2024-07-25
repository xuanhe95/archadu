package org.archadu.core.api;

import fm.last.musicbrainz.coverart.CoverArt;
import fm.last.musicbrainz.coverart.CoverArtArchiveClient;
import fm.last.musicbrainz.coverart.CoverArtImage;
import io.aesy.musicbrainz.client.MusicBrainzClient;
import io.aesy.musicbrainz.entity.Artist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;




import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Service
public class CoverArtApiService {
    private final MusicBrainzClient client;
    private final CoverArtArchiveClient coverArtArchiveClient;



    @Autowired
    public CoverArtApiService(CoverArtArchiveClient coverArtArchiveClient) {
        this.client = null;
        this.coverArtArchiveClient = coverArtArchiveClient;
    }

    public Artist getArtist(@RequestParam String artistId) {
        if(artistId == null || artistId.isEmpty()){
            return null;
        }
        return client.artist().withId(UUID.fromString(artistId)).lookup().get();
    }

    public List<String> getCoverArtByMbId(String mbid) {
        UUID uuid = UUID.fromString(mbid);
        List<String> urls = new ArrayList<>();
        CoverArt coverArt = null;
        try {
            coverArt = coverArtArchiveClient.getByMbid(uuid);
            if (coverArt != null) {
                for (CoverArtImage coverArtImage : coverArt.getImages()) {
                    urls.add(coverArtImage.getImageUrl());
                }
            }
        } catch (Exception e) {
            System.out.println("Error in getting cover art: " + e.getMessage());
        }
        return urls;
    }



}
