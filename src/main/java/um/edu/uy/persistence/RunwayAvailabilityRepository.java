package um.edu.uy.persistence;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import um.edu.uy.business.entities.Gates;
import um.edu.uy.business.entities.Runway;
import um.edu.uy.business.entities.RunwayAvailability;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RunwayAvailabilityRepository extends CrudRepository<RunwayAvailability,Long> {

    @Query(value = "SELECT r.runway FROM RunwayAvailability r WHERE r.runway.airport.ICAO = ?1 AND r.startOccupation <= ?2 AND r.endOccupation >= ?2")
    public List<Runway> occupiedRunways(String airportICAO, LocalDateTime flightTime);
}
