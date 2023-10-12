package um.edu.uy.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import um.edu.uy.persistence.GatesRepository;

@Service
public class GatesMgr {
    @Autowired
    GatesRepository gatesRepository;
}
