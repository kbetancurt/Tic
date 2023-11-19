package um.edu.uy.business;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import um.edu.uy.business.entities.Passenger;
import um.edu.uy.business.entities.PassengerFlight;
import um.edu.uy.business.entities.Vuelo;
import um.edu.uy.persistence.PassengerFlightRepository;
import um.edu.uy.persistence.PassengerRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class PassengerFlightMgr {
    @Autowired
    PassengerFlightRepository passengerFlightRepository;
    @Autowired
    PassengerRepository passengerRepository;

    public void addPassengerFlight(Passenger passenger, Vuelo vuelo) {
        PassengerFlight passengerFlight= new PassengerFlight(passenger,vuelo);
        passengerFlightRepository.save(passengerFlight);
    }
    public List<PassengerFlight> obtenerMapeoPasajero(String mail){
        Passenger passenger =passengerRepository.findOneByMail(mail);
        return passengerFlightRepository.findAllByPassenger_Id(passenger.getId());

    }
    public List<Vuelo> obtenerVuelosPasajero(List<PassengerFlight> listaVuelos){
        List<Vuelo> vuelos = new ArrayList<>();
        for (PassengerFlight passengerFlight:listaVuelos) {
            if (passengerFlight.getVuelo()!=null){
                vuelos.add(passengerFlight.getVuelo());}
        }
        return vuelos;

    }
    public PassengerFlight findPassengerFlight(Passenger passenger, Vuelo vuelo){
        return passengerFlightRepository.findByPassengerAndVuelo(passenger.getId(),vuelo.getId());
    }
    public Integer getVueloId(Vuelo vuelo) {
        return Math.toIntExact(vuelo.getId());

    }


}
