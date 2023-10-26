package um.edu.uy.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import um.edu.uy.business.entities.Airline;

@Repository
public interface AirlaneRepository extends CrudRepository<Airline, Long> {

    /**
     * Retorna un cliente por documento si encuentra mas de una lanza una excepcion
     * @param name
     * @return
     */
    public Airline findOneByName(String name);
    /**
     * Retorna un cliente por documento si encuentra mas de una lanza una excepcion
     * @param IATA
     * @return
     */
    public Airline findOneByIATA(String IATA);
    /**
     * Retorna un cliente por documento si encuentra mas de una lanza una excepcion
     * @param ICAO
     * @return
     */
    public Airline findOneByICAO(String ICAO);
    public Airline findAllByName(String name);

    public boolean existsByIATAAndICAO(String IATA, String ICAO);



}
