package org.archadu.core.api;

import fm.last.musicbrainz.coverart.CoverArt;
import fm.last.musicbrainz.coverart.CoverArtArchiveClient;
import fm.last.musicbrainz.coverart.CoverArtImage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;




import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Service
public class CoverArtApiService {
    private static final Logger log = LoggerFactory.getLogger(CoverArtApiService.class);
    private final TaskExecutor taskExecutor;

    private final CoverArtArchiveClient coverArtArchiveClient;
    private final RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public CoverArtApiService(CoverArtArchiveClient coverArtArchiveClient, RedisTemplate<String, Object> redisTemplate,
                              @Qualifier("taskExecutor") TaskExecutor taskExecutor) {
        log.info("CoverArtApiService");
        this.redisTemplate = redisTemplate;
        this.coverArtArchiveClient = coverArtArchiveClient;
        this.taskExecutor = taskExecutor;
    }


    public List<String> getCoverArtByMbId(String mbid) {

        long start = System.currentTimeMillis();
        String key = "coverart:" + mbid;

        List<String> urls = (List<String>) redisTemplate.opsForValue().get(key);

        if(urls != null) {
            log.info("Cache hit");
            log.info("Time taken: " + (System.currentTimeMillis() - start));
            return urls;
        }

        UUID uuid = UUID.fromString(mbid);
        urls = new ArrayList<>();
        CoverArt coverArt = null;
        try {
            coverArt = coverArtArchiveClient.getByMbid(uuid);
            if (coverArt != null) {
                for (CoverArtImage coverArtImage : coverArt.getImages()) {
                    urls.add(coverArtImage.getImageUrl());
                }
            }
        } catch (Exception e) {
            log.error("Error in getting cover art: " + e.getMessage());
        }
        redisTemplate.opsForValue().set(key, urls, 10, TimeUnit.HOURS);


        log.info("Cache miss");
        log.info("Time taken: " + (System.currentTimeMillis() - start));
        return urls;
    }

    public CompletableFuture<List<String>> getCoverArtByMbIdAsync(String mbid) {
        log.info("getCoverArtByMbIdAsync");
        return CompletableFuture.supplyAsync(() -> getCoverArtByMbId(mbid), taskExecutor)
                .exceptionally(ex -> {
            log.error("Error in getting cover art: " + ex.getMessage());
            return null;
        });
    }

}
