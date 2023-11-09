package um.edu.uy.business;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import um.edu.uy.Session;
import um.edu.uy.business.entities.Airport;
import um.edu.uy.business.entities.GateAvailability;
import um.edu.uy.business.entities.Gates;
import um.edu.uy.business.entities.Vuelo;
import um.edu.uy.persistence.AirportRepository;
import um.edu.uy.persistence.GateAvailabilityRepository;
import um.edu.uy.persistence.GatesRepository;
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

    public List<Gates> getAvailableGates(LocalDateTime startOccupation) {
        List<GateAvailability> occupiedGates = (List<GateAvailability>) gateAvailabilityRepository.findAll();
        Airport airport = airportRepository.findOneByICAO(Session.getInstance().getAirport());
        List<Gates> availableGates = gatesRepository.findAllByAirport(airport);
        occupiedGates.removeIf(gateAvailability -> !Objects.equals(gateAvailability.getGate().getAirport().getICAO(), Session.getInstance().getAirport()));
        occupiedGates.removeIf(gateAvailability -> !gateAvailability.getStartOccupation().isBefore(startOccupation) && !gateAvailability.getEndOccupation().isAfter(startOccupation));
        availableGates.removeIf(gate -> occupiedGates.stream().anyMatch(gateAvailability -> gateAvailability.getGate().equals(gate)));
        return availableGates;
    }

    public void occupyGate(Gates gate, Vuelo vuelo) {
        LocalDateTime startOccupation = vuelo.getHorarioSalidaEst().minusHours(1);
        LocalDateTime endOccupation = startOccupation.plusMinutes(90);
        GateAvailability gateAvailability = new GateAvailability(gate, startOccupation, endOccupation);
        save(gateAvailability);
    }

    public void save(GateAvailability gateAvailability) {
        gateAvailabilityRepository.save(gateAvailability);
    }
}
