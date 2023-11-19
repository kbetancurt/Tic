package um.edu.uy.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import um.edu.uy.business.entities.PassengerFlight;

import java.util.List;

@Repository
public interface PassengerFlightRepository extends CrudRepository<PassengerFlight, Long>{
    PassengerFlight findByPassenger_IdAndVuelo_Id(Long idPassenger, Long idVuelo);
    List<PassengerFlight> findAllByPassenger_Id(Long idPassenger);
    PassengerFlight findByPassengerAndVuelo(Long idPassenger, Long idVuelo);

}
