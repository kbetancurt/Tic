package um.edu.uy.business;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import um.edu.uy.business.entities.Passenger;
import um.edu.uy.business.entities.PassengerFlight;
import um.edu.uy.business.entities.Vuelo;
import um.edu.uy.persistence.PassengerFlightRepository;

@Service
public class PassengerFlightMgr {
    @Autowired
    PassengerFlightRepository passengerFlightRepository;

    public void addPassengerFlight(Passenger passenger, Vuelo vuelo) {
        PassengerFlight passengerFlight= new PassengerFlight(passenger,vuelo);
        passengerFlightRepository.save(passengerFlight);
    }

}
