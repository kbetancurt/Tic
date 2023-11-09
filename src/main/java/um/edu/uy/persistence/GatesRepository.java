package um.edu.uy.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import um.edu.uy.business.entities.Airport;
import um.edu.uy.business.entities.Gates;

import java.util.List;

@Repository
public interface GatesRepository extends CrudRepository<Gates,Long> {
    public List<Gates> findAllByAirport(Airport airport);
}
