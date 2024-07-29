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
import java.util.concurrent.CompletableFuture;

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

    @GetMapping("/async")
    public CompletableFuture<Response<List<String>>> getCoverArtByMbIdAsync(@RequestParam String mbid) {
        return coverArtApiService.getCoverArtByMbIdAsync(mbid)
            .thenApply(Response::success)
            .exceptionally(e -> Response.error("Error in getting cover art"));
    }

    @GetMapping("/async/three")
    public Response<List<List<String>>> getCoverArtByMbIdAsyncThree(@RequestParam String mbid1, @RequestParam String mbid2, @RequestParam String mbid3) {
        CompletableFuture<List<String>> coverArt1 = coverArtApiService.getCoverArtByMbIdAsync(mbid1);
        CompletableFuture<List<String>> coverArt2 = coverArtApiService.getCoverArtByMbIdAsync(mbid2);
        CompletableFuture<List<String>> coverArt3 = coverArtApiService.getCoverArtByMbIdAsync(mbid3);
        try {
            CompletableFuture.allOf(coverArt1, coverArt2, coverArt3).get();
            return Response.success(List.of(coverArt1.get(), coverArt2.get(), coverArt3.get()));
        } catch (Exception e) {
            log.error("Error in getting cover art: " + e.getMessage());
            return Response.error("Error in getting cover art");
        }
    }

    @GetMapping("/three")
    public Response<List<List<String>>> getCoverArtByMbIdThree(@RequestParam String mbid1, @RequestParam String mbid2, @RequestParam String mbid3) {
        List<String> coverArt1 = coverArtApiService.getCoverArtByMbId(mbid1);
        List<String> coverArt2 = coverArtApiService.getCoverArtByMbId(mbid2);
        List<String> coverArt3 = coverArtApiService.getCoverArtByMbId(mbid3);
        if(coverArt1 == null || coverArt2 == null || coverArt3 == null) {
            return Response.error("Error in getting cover art");
        }
        return Response.success(List.of(coverArt1, coverArt2, coverArt3));
    }



}
