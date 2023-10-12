package um.edu.uy.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import um.edu.uy.business.entities.Gates;

@Repository
public interface GatesRepository extends CrudRepository<Gates,Long> {
}
