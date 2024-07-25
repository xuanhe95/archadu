
package org.archadu.core.fm;
import org.archadu.core.fm.model.Area;
import org.archadu.core.fm.model.Artist;
import org.archadu.core.fm.model.ArtistType;
import org.archadu.core.fm.model.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
import java.util.List;

public interface ArtistRepo extends JpaRepository<Artist, UUID> {
}
