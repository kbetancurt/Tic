package um.edu.uy.persistence;

import org.springframework.data.repository.CrudRepository;
import um.edu.uy.business.entities.AeroportEmployee;

public interface AeroportEmployeeRepository extends CrudRepository<AeroportEmployee, Long> {

    /**
     * Retorna un cliente por documento si encuentra mas de una lanza una excepcion
     * @param passport
     * @return
     */
    AeroportEmployee findOneByPassport(String passport);

}
