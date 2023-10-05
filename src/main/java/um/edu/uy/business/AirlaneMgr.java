package um.edu.uy.business;
import org.springframework.stereotype.Service;
import um.edu.uy.business.entities.Airlane;
import um.edu.uy.persistence.AirlaneRepository;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class AirlaneMgr {
    @Autowired
    private AirlaneRepository airlaneRepository;
    public void addAirlane(Airlane airlane){
        if (airlaneRepository.findOneByName(airlane.getName())!=null || airlaneRepository.findOneByIATA(airlane.getIATA())!=null || airlaneRepository.findOneByICAO(airlane.getICA0())!=null) {
            return;
        }
        if (airlane.ICAO.length()!=3 || airlane.IATA.length()!=2){
            return;
        }
        airlaneRepository.save(airlane);

    }
}
