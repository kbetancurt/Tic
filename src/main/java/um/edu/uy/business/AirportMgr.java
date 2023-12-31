package um.edu.uy.business;
import org.springframework.stereotype.Service;
import um.edu.uy.Session;
import um.edu.uy.business.entities.Airport;
import org.springframework.beans.factory.annotation.Autowired;
import um.edu.uy.business.entities.Avion;
import um.edu.uy.persistence.AirportRepository;
import um.edu.uy.persistence.AvionRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class AirportMgr {
    @Autowired
    private AirportRepository airportRepository;


    public List<String> airportNameList(){
        List<String>airportNames = new ArrayList<>();
        List<Airport> airports = (List<Airport>) airportRepository.findAll();
        airports.forEach(airport -> { airportNames.add(airport.getName());});
        return airportNames;

    }

    public List<String> airportICAOList(){
        List<String>airportsICAO = new ArrayList<>();
        List<Airport> airports = (List<Airport>) airportRepository.findAll();
        airports.forEach(airport -> { airportsICAO.add(airport.getICAO());});
        return airportsICAO;
    }


}