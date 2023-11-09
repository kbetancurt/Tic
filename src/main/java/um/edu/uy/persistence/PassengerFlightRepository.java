package um.edu.uy.persistence;

import org.springframework.data.repository.CrudRepository;
import um.edu.uy.business.entities.PassengerFlight;
public interface PassengerFlightRepository extends CrudRepository<PassengerFlight, Long>{
}
