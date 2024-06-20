package org.archadu.core.model.id;

import java.io.Serializable;
import java.util.Objects;

public class PlaylistTrackId implements Serializable {
    private String playlistId;
    private String trackId;

    public PlaylistTrackId() {
    }

    public PlaylistTrackId(String playlistId, String trackId) {
        this.playlistId = playlistId;
        this.trackId = trackId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlaylistTrackId that = (PlaylistTrackId) o;

        return playlistId.equals(that.playlistId) && trackId.equals(that.trackId);
    }

    @Override
    public int hashCode(){
        return Objects.hash(playlistId, trackId);
    }


}
