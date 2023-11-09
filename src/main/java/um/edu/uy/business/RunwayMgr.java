package um.edu.uy.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import um.edu.uy.persistence.RunwayRepository;

@Service
public class RunwayMgr {
    @Autowired
    RunwayRepository runwayRepository;
}
