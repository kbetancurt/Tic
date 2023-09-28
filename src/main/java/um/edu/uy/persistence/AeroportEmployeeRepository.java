package um.edu.uy.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import um.edu.uy.business.entities.AeroportEmployee;

@Repository
public interface AeroportEmployeeRepository extends CrudRepository<AeroportEmployee, Long> {

    /**
     * Retorna un cliente por documento si encuentra mas de una lanza una excepcion
     * @param passport
     * @return
     */
    public AeroportEmployee findOneByPassport(String passport);
    public AeroportEmployee findOneByMail(String mail);



}
