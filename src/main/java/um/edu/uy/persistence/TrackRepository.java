package um.edu.uy.persistence;

import org.springframework.data.repository.CrudRepository;
import um.edu.uy.business.entities.Track;

public interface TrackRepository extends CrudRepository<Track, Long> {
}
