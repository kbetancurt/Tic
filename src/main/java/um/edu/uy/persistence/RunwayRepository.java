package um.edu.uy.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import um.edu.uy.business.entities.Runway;

import java.util.List;

@Repository
public interface RunwayRepository extends CrudRepository<Runway, Long> {
    public List<Runway> findByAirport_ICAO(String ICAO);
}
