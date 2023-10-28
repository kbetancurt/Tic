package um.edu.uy.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import um.edu.uy.Session;
import um.edu.uy.business.entities.Avion;
import um.edu.uy.persistence.AvionRepository;

import java.util.ArrayList;
import java.util.List;


@Service
public class AvionMgr {
    @Autowired
    private AvionRepository avionRepository;
    public void addAvion(Avion avion){
        avionRepository.save(avion);
    }

    public List<String> planeList() {
        List<String> planeNames = new ArrayList<>();
        List<Avion> aviones = (List<Avion>) avionRepository.findAll();
        for (Avion avion:aviones) {
            if (avion.getId_aerolinea()== Session.getInstance().getAirline()) {
                planeNames.add(avion.getICAO()+" "+avion.getModel());
            }
        }
        return planeNames;
    }
}
