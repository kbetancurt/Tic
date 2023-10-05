package um.edu.uy.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import um.edu.uy.business.entities.Airlane;
import org.springframework.data.repository.RepositoryDefinition;

@Repository
public interface AirlaneRepository extends CrudRepository<Airlane, Long> {

    /**
     * Retorna un cliente por documento si encuentra mas de una lanza una excepcion
     * @param name
     * @return
     */
    public Airlane findOneByName(String name);
    /**
     * Retorna un cliente por documento si encuentra mas de una lanza una excepcion
     * @param IATA
     * @return
     */
    public Airlane findOneByIATA(String IATA);
    /**
     * Retorna un cliente por documento si encuentra mas de una lanza una excepcion
     * @param ICAO
     * @return
     */
    public Airlane findOneByICAO(String ICAO);



}
