package um.edu.uy.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import um.edu.uy.business.entities.GateAvailability;
import um.edu.uy.business.entities.Gates;

import java.time.LocalDateTime;

@Repository
public interface GateAvailabilityRepository extends CrudRepository<GateAvailability,Long> {

}
