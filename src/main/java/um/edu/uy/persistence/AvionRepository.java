package um.edu.uy.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import um.edu.uy.business.entities.Avion;

@Repository
public interface AvionRepository extends CrudRepository<Avion, Long> {
    public Avion findOneById(Long Id);

}
