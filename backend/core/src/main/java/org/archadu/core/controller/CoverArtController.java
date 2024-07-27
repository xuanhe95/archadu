package org.archadu.core.api;

import io.aesy.musicbrainz.client.MusicBrainzClient;
import io.aesy.musicbrainz.entity.Artist;
import org.archadu.core.dto.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/cover")
public class CoverArtController {
    private final Logger log = LoggerFactory.getLogger(CoverArtController.class);
    private final CoverArtApiService coverArtApiService;
    @Autowired
    public CoverArtController(CoverArtApiService coverArtApiService) {
        log.info("CoverArtController created");
        this.coverArtApiService = coverArtApiService;
    }


//    curl --location 'http://localhost:8080/api/cover?mbid=76df3287-6cda-33eb-8e9a-044b5e15ffdd' \
//            --header 'Cookie: satoken=56bf5675-5774-4d1e-aba5-903b52f636a6; satoken=56bf5675-5774-4d1e-aba5-903b52f636a6'
    @GetMapping()
    public Response<List<String>> getCoverArtByMbId(@RequestParam String mbid) {
        final String testId = "76df3287-6cda-33eb-8e9a-044b5e15ffdd";

        List<String> urls = coverArtApiService.getCoverArtByMbId(mbid);
        if(urls == null) {
            return Response.error("Error in getting cover art");
        }
        return Response.success(urls);
    }



}
