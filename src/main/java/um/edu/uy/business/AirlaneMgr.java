package um.edu.uy.business;
import org.springframework.stereotype.Service;
import um.edu.uy.business.entities.Airline;
import um.edu.uy.business.exceptions.AirlineAlreadyExists;
import um.edu.uy.business.exceptions.InvalidAirlineInformation;
import um.edu.uy.persistence.AirlaneRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Service
public class AirlaneMgr {
    @Autowired
    private AirlaneRepository airlaneRepository;
    public void addAirlane(Airline airline) throws AirlineAlreadyExists, InvalidAirlineInformation {
        if (airlaneRepository.findOneByName(airline.getName())!=null || airlaneRepository.findOneByIATA(airline.getIATA())!=null || airlaneRepository.findOneByICAO(airline.getICAO())!=null) {
            throw new AirlineAlreadyExists();
        }
        if (airline.ICAO.length()!=3 || airline.IATA.length()!=2){
            throw new InvalidAirlineInformation("Datos Inválidos");
        }
        airlaneRepository.save(airline);

    }
    public List<String> obtenerAerolineas() {
        List<String> airlineName= new ArrayList<>();
        List<Airline> airlines = (List<Airline>) airlaneRepository.findAll();
        airlines.forEach(airline -> {airlineName.add(airline.getName());});
        return airlineName;
    }
}
