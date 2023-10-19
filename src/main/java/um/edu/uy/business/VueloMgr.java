package um.edu.uy.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import um.edu.uy.business.entities.Vuelo;
import um.edu.uy.persistence.AirlaneRepository;
import um.edu.uy.persistence.VueloRepository;

import java.util.List;

@Service
public class VueloMgr {
    @Autowired
    VueloRepository vueloRepository;
    @Autowired
    AirlaneRepository airlaneRepository;
    public void addVuelo( Vuelo vuelo){
        if (airlaneRepository.findOneByICAO(vuelo.ICAO)==null || airlaneRepository.findOneByIATA(vuelo.getIATAAerolinea())==null) {}
    }
    public void actualizarVueloLLegada(Vuelo vuelo,boolean act){
        vuelo.aprobadoLLegada=act;
    }
    public void actualizarVueloSalida(Vuelo vuelo, boolean act){
        vuelo.aprobadoSalida=act;
    }
    public List<Vuelo> obtenerVuelosSalenAeropuerto(String aeropuerto){
        return (List<Vuelo>) vueloRepository.findAllByAeropuertoOrigen(aeropuerto);

    }
    public List<Vuelo> obtenerVuelosLLeganAeropuerto(String aeropuerto){
        return (List<Vuelo>) vueloRepository.findAllByAeropuertoDestino(aeropuerto);

    }
}
