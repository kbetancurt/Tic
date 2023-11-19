package um.edu.uy.persistence;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import um.edu.uy.business.entities.Airline;
import um.edu.uy.business.entities.Vuelo;

import java.util.List;

@Repository
public interface VueloRepository extends CrudRepository<Vuelo,Long> {
    public List<Vuelo> findAllByICAOaerolinea(String ICAOaerolinea);
    public Vuelo findOneById(long id);

    public Vuelo findOneByNumero(Long numero);

    @Query(value = "SELECT v FROM Vuelo v WHERE v.aeropuertoOrigen = ?1 OR v.aeropuertoDestino = ?1 AND v.horarioSalidaEst >= CURRENT_TIMESTAMP")
    public List<Vuelo> findAllByAeropuertoOrigenOrAeropuertoDestinoAndHorarioSalidaEstAfter(String ICAOaeropuerto);
}
