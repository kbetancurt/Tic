package um.edu.uy.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import um.edu.uy.business.entities.Runway;
@Repository
public interface RunwayRepository extends CrudRepository<Runway, Long> {
}
