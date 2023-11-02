package um.edu.uy.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import um.edu.uy.business.entities.GateAvailability;
import um.edu.uy.business.entities.Gates;

import java.time.LocalDateTime;

@Repository
public interface GateAvailabilityRepository extends CrudRepository<GateAvailability,Long> {
    public GateAvailability findOneByGateId(long gateId);

    public GateAvailability findOneByStartOccupation(LocalDateTime startOccupation);

    public GateAvailability findOneByEndOccupation(LocalDateTime endOccupation);

    public GateAvailability findOneByGateAndStartOccupation(Gates gate, LocalDateTime startOccupation);

    public GateAvailability findAllByGateAndStartOccupation(Gates gate, LocalDateTime startOccupation);

    public GateAvailability findAllByStartOccupation(LocalDateTime startOccupation);


}
