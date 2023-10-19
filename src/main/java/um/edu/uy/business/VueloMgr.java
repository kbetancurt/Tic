package um.edu.uy.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import um.edu.uy.business.entities.Vuelo;
import um.edu.uy.persistence.AirlaneRepository;
import um.edu.uy.persistence.VueloRepository;

@Service
public class VueloMgr {
    @Autowired
    VueloRepository vueloRepository;
    @Autowired
    AirlaneRepository airlaneRepository;
    public void addVuelo( Vuelo vuelo){
        if (airlaneRepository.findOneByICAO(vuelo.ICAO)==null || airlaneRepository.findOneByIATA(vuelo.getIATAAerolinea())==null) {}
    }

}
