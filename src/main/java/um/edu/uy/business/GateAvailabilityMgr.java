package um.edu.uy.business;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import um.edu.uy.business.entities.GateAvailability;
import um.edu.uy.business.entities.Gates;
import um.edu.uy.persistence.GateAvailabilityRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class GateAvailabilityMgr {

    @Autowired
    private GateAvailabilityRepository gateAvailabilityRepository;
    public List<GateAvailability> getAvailableGates(LocalDateTime startOccupation) {
        List<GateAvailability> allGates = (List<GateAvailability>) gateAvailabilityRepository.findAll();
        allGates.removeIf(gateAvailability -> gateAvailability.getStartOccupation().isBefore(startOccupation) && gateAvailability.getEndOccupation().isAfter(startOccupation));
        return allGates;
    }
}
