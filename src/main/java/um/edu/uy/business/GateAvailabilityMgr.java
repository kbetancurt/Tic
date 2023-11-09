package um.edu.uy.business;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import um.edu.uy.Session;
import um.edu.uy.business.entities.Airport;
import um.edu.uy.business.entities.GateAvailability;
import um.edu.uy.business.entities.Gates;
import um.edu.uy.business.entities.Vuelo;
import um.edu.uy.persistence.AirportRepository;
import um.edu.uy.persistence.GateAvailabilityRepository;
import um.edu.uy.persistence.GatesRepository;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class GateAvailabilityMgr {

    @Autowired
    private GateAvailabilityRepository gateAvailabilityRepository;

    @Autowired
    private AirportRepository airportRepository;

    @Autowired
    private GatesRepository gatesRepository;

    public List<Gates> getAvailableGates(LocalDateTime departureTime) {
        String airport =Session.getInstance().getAirport();
        List<Gates> occupiedGates = gateAvailabilityRepository.occupiedGates(airport, departureTime);
        List<Gates> availableGates = gatesRepository.findByAirport_ICAO(airport);
        //remove from available gates the occupied gates by id
        availableGates.removeIf(gate -> occupiedGates.stream().anyMatch(occupiedGate -> Objects.equals(occupiedGate.getId(), gate.getId())));
        return availableGates;
    }

    public void occupyGate(Gates gate, Vuelo vuelo) {
        LocalDateTime startOccupation = vuelo.getHorarioSalidaEst().minusHours(1);
        LocalDateTime endOccupation = startOccupation.plusMinutes(90);
        GateAvailability gateAvailability = new GateAvailability(gate, startOccupation, endOccupation);
        gateAvailability.setFlight(vuelo);
        save(gateAvailability);
    }

    public void save(GateAvailability gateAvailability) {
        gateAvailabilityRepository.save(gateAvailability);
    }
}
