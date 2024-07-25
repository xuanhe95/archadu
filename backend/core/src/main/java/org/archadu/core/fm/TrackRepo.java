package org.archadu.core.fm;

import org.archadu.core.fm.model.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface TrackRepo extends JpaRepository<Track, Integer> {
    // 通过 gid 查找 Track
    Track findByGid(UUID gid);

    List<Track> findByPosition(int position);

    // 通过长度查找 Track
    List<Track> findByLength(Integer length);
}
