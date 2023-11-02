package um.edu.uy.ui.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import um.edu.uy.business.GateAvailabilityMgr;
import um.edu.uy.business.entities.GateAvailability;

import java.util.List;

@Component
public class SelectGateController {
    @Autowired
    private GateAvailabilityMgr gateAvailabilityMgr;


    public void initialize() {
//        List<GateAvailability> availableGates = gateAvailabilityMgr.getAvailableGates();
    }
}
