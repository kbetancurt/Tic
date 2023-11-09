package um.edu.uy.persistence;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import um.edu.uy.business.entities.Airport;
import um.edu.uy.business.entities.GateAvailability;
import um.edu.uy.business.entities.Gates;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface GateAvailabilityRepository extends CrudRepository<GateAvailability,Long> {
    @Query(value = "SELECT g.gate FROM GateAvailability g WHERE g.gate.airport.ICAO = ?1 AND g.startOccupation <= ?2 AND g.endOccupation >= ?2")
    public List<Gates> occupiedGates(String airportICAO, LocalDateTime flightTime);





}
