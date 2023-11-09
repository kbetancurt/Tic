package um.edu.uy.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import um.edu.uy.business.entities.Passenger;
import um.edu.uy.business.entities.Vuelo;
import um.edu.uy.persistence.PassengerRepository;

@Service
public class PassengerMgr {
    @Autowired
    private PassengerRepository passengerRepository;
    public void addPassenger(Passenger passenger) {
        passengerRepository.save(passenger);
    }
    public boolean existsPassenger(String passport) {
        return passengerRepository.existsByPassport(passport);
    }
    public String generateMail(String nombre, String apellido) {
        String newmail=Character.toLowerCase(nombre.charAt(0))+apellido.toLowerCase()+"@passenger.com";
        if (passengerRepository.findOneByMail(newmail)!=null) {
            int i=1;
            while (passengerRepository.findOneByMail(newmail)!=null) {
                newmail=Character.toLowerCase(nombre.charAt(0))+apellido.toLowerCase()+i+"@passenger.com";
                i++;
            }
        }
        return newmail;
    }
    public Passenger getPassenger(String passport) {
        return passengerRepository.findOneByPassport(passport);
    }

}
