package org.archadu.core.dto;


public record PlaylistRequest(
        String playlistId,
        Long userId,
        String playlistName,
        String description,
        String coverUrl,
        boolean shared,
        boolean collaborative
) {}
