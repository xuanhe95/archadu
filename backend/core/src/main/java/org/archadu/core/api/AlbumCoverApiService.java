package org.archadu.core.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;


@Service
public class AlbumCoverApiService {

    private static final String MB_RELEASE_URL = "https://musicbrainz.org/ws/2/release-group/?query=release:%s&fmt=json";
    private static final String CA_RELEASE_URL= "https://coverartarchive.org/release-group/";

    private final RestTemplate restTemplate;
    @Autowired
    public AlbumCoverApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getAlbumCover(String title){
        String id = getMusicBrainzIdFromTitle(title);
        if(id == null || id.isEmpty()){
            return "";
        }
        String url = CA_RELEASE_URL + id;
        try{
            ResponseEntity<CoverArtResponse> response = restTemplate.getForEntity(url, CoverArtResponse.class);
            if(response.getStatusCode().isError()){
                return "";
            }
            CoverArtResponse coverArtResponse = response.getBody();
            if(coverArtResponse != null){
                return coverArtResponse.getImage();
            } else {
                return "";
            }
        } catch(Exception e) {
            System.out.println("Error in getting album cover: " + e.getMessage());
        }
        return "";
    }

    public String getMusicBrainzIdFromTitle(String title) {
        String query = URLEncoder.encode(title, StandardCharsets.UTF_8);
        String url = String.format(MB_RELEASE_URL, query);

        try{
            ResponseEntity<MusicBrainzResponse> response = restTemplate.getForEntity(url, MusicBrainzResponse.class);
            if(response.getStatusCode().isError()){
                return "";
            }
            MusicBrainzResponse musicBrainzResponse = response.getBody();
            if (musicBrainzResponse != null && musicBrainzResponse.getReleaseGroups() != null && !musicBrainzResponse.getReleaseGroups().isEmpty()) {
                return musicBrainzResponse.getReleaseGroups().get(0);
            } else {
                return "";
            }

        } catch(Exception e) {
            System.out.println("Error in getting musicbrainz id from title: " + e.getMessage());
        }
        return "";
    }

    public static class MusicBrainzResponse {
        private List<String> releaseGroups;

        public List<String> getReleaseGroups() {
            return releaseGroups;
        }

        public void setReleaseGroups(List<String> releaseGroups) {
            this.releaseGroups = releaseGroups;
        }
    }

    public static class CoverArtResponse {
        private String image;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }
    }

}
