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

import java.util.ArrayList;
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

//    curl --location 'http://localhost:8080/api/cover/list?mbids=76df3287-6cda-33eb-8e9a-044b5e15ffdd%2Cc9f91cdc-984e-4303-9a51-4ac0dfa2348f%2C8b8a38a9-a290-4560-84f6-3d4466e8d791' \
//            --header 'Cookie: satoken=56bf5675-5774-4d1e-aba5-903b52f636a6; satoken=56bf5675-5774-4d1e-aba5-903b52f636a6'
    @GetMapping("/list")
    public Response<List<List<String>>> getCoverArtByMbIdList(@RequestParam List<String> mbids) {
        List<List<String>> urls = new ArrayList<>();

        for(String mbid : mbids) {
            List<String> coverArt = coverArtApiService.getCoverArtByMbId(mbid);
            if(coverArt == null) {
                return Response.error("Error in getting cover art");
            }
            urls.add(coverArt);
        }

        return Response.success(urls);
    }

//    curl --location 'http://localhost:8080/api/cover/list/async?mbids=76df3287-6cda-33eb-8e9a-044b5e15ffdd%2Cc9f91cdc-984e-4303-9a51-4ac0dfa2348f%2C8b8a38a9-a290-4560-84f6-3d4466e8d791' \
//            --header 'Cookie: satoken=56bf5675-5774-4d1e-aba5-903b52f636a6; satoken=56bf5675-5774-4d1e-aba5-903b52f636a6'

    @GetMapping("/list/async")
    public CompletableFuture<Response<List<List<String>>>> getCoverArtByMbIdListAsync(@RequestParam List<String> mbids) {
        List<CompletableFuture<List<String>>> futures = new ArrayList<>();

        for(String mbid : mbids) {
            futures.add(coverArtApiService.getCoverArtByMbIdAsync(mbid));
        }

        return CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
            .thenApply(v -> {
                List<List<String>> urls = new ArrayList<>();
                for(CompletableFuture<List<String>> future : futures) {
                    try {
                        urls.add(future.get());
                    } catch (Exception e) {
                        log.error("Error in getting cover art: " + e.getMessage());
                        return Response.error("Error in getting cover art");
                    }
                }
                return Response.success(urls);
            });
    }



}
