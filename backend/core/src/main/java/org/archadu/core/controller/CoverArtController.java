package org.archadu.core.api;

import io.aesy.musicbrainz.client.MusicBrainzClient;
import io.aesy.musicbrainz.entity.Artist;
import org.archadu.core.dto.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/mb")
public class CoverArtController {
    private MusicBrainzClient client;
    private CoverArtApiService coverArtApiService;
    @Autowired
    public CoverArtController(CoverArtApiService coverArtApiService) {

        System.out.println("MusicbrainzApiService");
        this.client = null;
        this.coverArtApiService = coverArtApiService;
    }

    @GetMapping("/artist")
    public Artist getArtist(@RequestParam String artistId) {
        if(artistId == null || artistId.isEmpty()){
            return null;
        }
        return client.artist().withId(UUID.fromString(artistId)).lookup().get();
    }

    @GetMapping("/coverart")
    public Response<List<String>> getCoverArtByMbId(@RequestParam String mbid) {
        final String testId = "76df3287-6cda-33eb-8e9a-044b5e15ffdd";
        List<String> urls = coverArtApiService.getCoverArtByMbId(mbid);
        return new Response<List<String>>("Success" ,urls);
    }



}
