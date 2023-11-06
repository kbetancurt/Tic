package um.edu.uy.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.stereotype.Repository;
import um.edu.uy.business.entities.Airport;
@Repository
public interface AirportRepository extends CrudRepository<Airport, Long> {

   public Airport findOneByICAO(String airport);

   public Airport findOneByName(String airportName);

   public Airport findByICAO(String airport);
}