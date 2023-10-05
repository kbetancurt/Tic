package um.edu.uy.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import um.edu.uy.business.entities.Avion;
import um.edu.uy.persistence.AvionRepository;


@Service
public class AvionMgr {
    @Autowired
    private AvionRepository avionRepository;
    public void addAvion(Avion avion){
        avionRepository.save(avion);
    }
}
