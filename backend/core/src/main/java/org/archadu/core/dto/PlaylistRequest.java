package org.archadu.core.dto;


public record PlaylistRequest(
        String playlistId,
        Long userId,          // User 的 ID
        String playlistName,
        String description,
        String coverUrl,
        boolean isPublic,
        boolean isCollaborative
) {}
