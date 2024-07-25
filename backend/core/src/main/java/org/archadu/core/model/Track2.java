package org.archadu.core.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "track")
public class Track2 {
    @Id
    private String trackId;

    @Column(name = "cover_url", nullable = true)
    private String coverUrl;

    public Track2() {
    }

    public Track2(String trackId) {
        this.trackId = trackId;
    }

    public String getTrackId() {
        return trackId;
    }

    public void setTrackId(String trackId) {
        this.trackId = trackId;
    }
}
