package um.edu.uy.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import um.edu.uy.business.entities.Airline;
import um.edu.uy.business.entities.Vuelo;

import java.util.List;

@Repository
public interface VueloRepository extends CrudRepository<Vuelo,Long> {
    public Vuelo findOneByICAOaerolinea(String ICAOAerolinea);
    public Vuelo findAllByAeropuertoDestino(String aeropuertoDestino);
    public Vuelo findAllByAeropuertoOrigen(String aeropuertoOrigen);
    public List<Vuelo> findAllByICAOaerolinea(String ICAOaerolinea);
    public Vuelo findOneById(long id);
    public Vuelo findByNumero(long flightNumber);

    public Vuelo findOnebyNumero(Long numero);
}
