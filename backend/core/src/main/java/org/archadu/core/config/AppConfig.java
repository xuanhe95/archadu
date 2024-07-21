package org.archadu.core.config;

import fm.last.musicbrainz.coverart.CoverArtArchiveClient;
import fm.last.musicbrainz.coverart.impl.DefaultCoverArtArchiveClient;
import io.aesy.musicbrainz.client.MusicBrainzClient;
import io.aesy.musicbrainz.client.MusicBrainzJerseyClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
   @Bean
    public CoverArtArchiveClient coverArtArchiveClient() {
        return new DefaultCoverArtArchiveClient();
    }



}
