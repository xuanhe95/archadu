package org.archadu.core.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "playlists")
public class Playlist {
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private String id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "name", nullable = false)
    private String playlistName;

    @Column(name = "description")
    private String description;

    @Column(name = "cover_url")
    private String coverUrl;

    @Column(name = "shared", nullable = false)
    private boolean shared;

    @Column(name = "collaborative", nullable = false)
    private boolean collaborative;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public Playlist()  {
    }

    public Playlist(String id, User user, String playlistName, String description, String coverUrl, boolean shared, boolean collaborative) {
        this.id = id;
        this.user = user;
        this.playlistName = playlistName;
        this.description = description;
        this.coverUrl = coverUrl;
        this.shared = shared;
        this.collaborative = collaborative;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setShared(boolean shared) {
        this.shared = shared;
    }

    public void setCollaborative(boolean collaborative) {
        this.collaborative = collaborative;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

    public String getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public String getDescription() {
        return description;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public boolean isShared() {
        return shared;
    }

    public boolean isCollaborative() {
        return collaborative;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }







}
