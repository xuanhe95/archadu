package org.archadu.core.model.ids;

import java.io.Serializable;
import java.util.Objects;

public class PlaylistTrackId implements Serializable {

    private String playlist;
    private String track;

    public PlaylistTrackId() {
    }

    public PlaylistTrackId(String playlist, String track) {
        this.playlist = playlist;
        this.track = track;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlaylistTrackId that = (PlaylistTrackId) o;

        return playlist.equals(that.playlist) && track.equals(that.track);
    }

    @Override
    public int hashCode(){
        return Objects.hash(playlist, track);
    }


}
