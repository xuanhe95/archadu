package org.archadu.core.model;

import jakarta.persistence.*;
import org.archadu.core.model.id.PlaylistTrackId;

import java.time.LocalDateTime;

@Entity

@Table(name = "playlist_track")
@IdClass(PlaylistTrackId.class)
public class PlaylistTrack {


    @Id
    @ManyToOne
    @JoinColumn(name = "playlist_id", nullable = false)
    private Playlist playlist;

    @Id
    @ManyToOne
    @JoinColumn(name = "track_id", nullable = false)
    private Track track;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

}
