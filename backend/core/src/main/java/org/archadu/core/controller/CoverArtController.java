package org.archadu.core.api;

import io.aesy.musicbrainz.client.MusicBrainzClient;
import io.aesy.musicbrainz.entity.Artist;
import org.archadu.core.dto.Response;
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
@RequestMapping("/api/mb")
public class CoverArtController {
    private final MusicBrainzClient client;
    private final CoverArtApiService coverArtApiService;
    private final RedisTemplate<String, Object> redisTemplate;
    @Autowired
    public CoverArtController(CoverArtApiService coverArtApiService, RedisTemplate<String, Object> redisTemplate) {

        System.out.println("MusicbrainzApiService");
        this.client = null;
        this.redisTemplate = redisTemplate;
        this.coverArtApiService = coverArtApiService;
    }

    @GetMapping("/artist")
    public Artist getArtist(@RequestParam String artistId) {
        if(artistId == null || artistId.isEmpty()){
            return null;
        }
        return client.artist().withId(UUID.fromString(artistId)).lookup().get();
    }

//    curl --location 'http://localhost:8080/api/mb/coverart?mbid=76df3287-6cda-33eb-8e9a-044b5e15ffdd' \
//            --header 'Cookie: satoken=56bf5675-5774-4d1e-aba5-903b52f636a6'
    @GetMapping("/coverart")
    public Response<List<String>> getCoverArtByMbId(@RequestParam String mbid) {
        final String testId = "76df3287-6cda-33eb-8e9a-044b5e15ffdd";

        long start = System.currentTimeMillis();

        String key = "coverart:" + mbid;

        List<String> urls = (List<String>) redisTemplate.opsForValue().get(key);
        if (urls != null) {
            System.out.println("Cache hit");
            System.out.println("Time taken: " + (System.currentTimeMillis() - start));
            return new Response<List<String>>("Success", urls);
        }
        urls = coverArtApiService.getCoverArtByMbId(mbid);
        redisTemplate.opsForValue().set(key, urls);
        System.out.println("Cache miss");
        System.out.println("Time taken: " + (System.currentTimeMillis() - start));

        return new Response<List<String>>("Success" ,urls);
    }



}