package um.edu.uy.business;
import org.springframework.stereotype.Service;
import um.edu.uy.business.entities.Airline;
import um.edu.uy.business.exceptions.AirlineAlreadyExists;
import um.edu.uy.business.exceptions.InvalidAirlineInformation;
import um.edu.uy.persistence.AirlineRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Service
public class AirlaneMgr {
    @Autowired
    private AirlineRepository airlineRepository;
    public void addAirlane(Airline airline) throws AirlineAlreadyExists, InvalidAirlineInformation {
        if (airlineRepository.findOneByName(airline.getName())!=null || airlineRepository.findOneByIATA(airline.getIATA())!=null || airlineRepository.findOneByICAO(airline.getICAO())!=null) {
            throw new AirlineAlreadyExists();
        }
        if (airline.ICAO.length()!=3 || airline.IATA.length()!=2){
            throw new InvalidAirlineInformation("Datos Inválidos");
        }
        airlineRepository.save(airline);

    }
    public List<String> obtenerAerolineas() {
        List<String> airlineName= new ArrayList<>();
        List<Airline> airlines = (List<Airline>) airlineRepository.findAll();
        airlines.forEach(airline -> {airlineName.add(airline.getName());});
        return airlineName;
    }
    public Airline obtenerAerolinea(long Id){
        return airlineRepository.findOneById(Id);
    }
}
