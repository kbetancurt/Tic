package um.edu.uy.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import um.edu.uy.Session;
import um.edu.uy.business.entities.GateAvailability;
import um.edu.uy.business.entities.Runway;
import um.edu.uy.business.entities.RunwayAvailability;
import um.edu.uy.business.entities.Vuelo;
import um.edu.uy.persistence.AirportRepository;
import um.edu.uy.persistence.RunwayAvailabilityRepository;
import um.edu.uy.persistence.RunwayRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class RunwayAvailabilityMgr {
    @Autowired
    private RunwayAvailabilityRepository runwayAvailabilityRepository;

    @Autowired
    private AirportRepository airportRepository;

    @Autowired
    private RunwayRepository runwayRepository;

    public List<Runway> getAvailableRunways(LocalDateTime departureTime) {
        String airport = Session.getInstance().getAirport();
        List<Runway> occupiedRunways = runwayAvailabilityRepository.occupiedRunways(airport, departureTime);
        List<Runway> availableRunways = runwayRepository.findByAirport_ICAO(airport);
        availableRunways.removeIf(runway -> occupiedRunways.stream().anyMatch(occupiedRunway -> Objects.equals(occupiedRunway.getId(), runway.getId())));
        return availableRunways;
    }

    public void occupyRunway(Runway runway, Vuelo vuelo) {
        LocalDateTime startOccupation = vuelo.getHorarioSalidaEst().minusMinutes(15);
        LocalDateTime endOccupation = startOccupation.plusMinutes(15);
        RunwayAvailability runwayAvailability = new RunwayAvailability(runway, startOccupation, endOccupation,vuelo);
        save(runwayAvailability);
    }

    public void save(RunwayAvailability runwayAvailability) {
        runwayAvailabilityRepository.save(runwayAvailability);
    }





}
