package org.archadu.core.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_playlists")
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "name", nullable = false)
    private String playlistName;

    @Column(name = "description")
    private String description;

    @Column(name = "is_public", nullable = false)
    private boolean isPublic;

    @Column(name = "is_collaborative", nullable = false)
    private boolean isCollaborative;

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

    public Playlist(User user, String playlistName, String description, boolean isPublic, boolean isCollaborative) {
        this.user = user;
        this.playlistName = playlistName;
        this.description = description;
        this.isPublic = isPublic;
        this.isCollaborative = isCollaborative;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setIsPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }

    public void setIsCollaborative(boolean isCollaborative) {
        this.isCollaborative = isCollaborative;
    }





}
