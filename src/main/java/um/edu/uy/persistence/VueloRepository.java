package um.edu.uy.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import um.edu.uy.business.entities.Airline;
import um.edu.uy.business.entities.Vuelo;

import java.util.List;

@Repository
public interface VueloRepository extends CrudRepository<Vuelo,Long> {
    public Vuelo findOneByICAOaerolinea(String IATAAerolinea);
    public Vuelo findAllByAeropuertoDestino(String aeropuertoDestino);
    public Vuelo findAllByAeropuertoOrigen(String aeropuertoOrigen);

}
