package um.edu.uy.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import um.edu.uy.business.entities.Track;
@Repository
public interface TrackRepository extends CrudRepository<Track, Long> {
}
