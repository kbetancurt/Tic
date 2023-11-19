package um.edu.uy.persistence;

import org.springframework.stereotype.Repository;
import um.edu.uy.business.entities.Passenger;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface PassengerRepository extends CrudRepository<Passenger,Long>{
    Passenger findOneByMail(String mail);
    Passenger findOneByPassport(String passport);
    boolean existsByPassport(String passport);



}
