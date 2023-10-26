package um.edu.uy.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import um.edu.uy.business.entities.Airline;
import um.edu.uy.business.entities.Vuelo;
import um.edu.uy.business.exceptions.InvalidFlightInformation;
import um.edu.uy.persistence.AirlaneRepository;
import um.edu.uy.persistence.VueloRepository;

import java.util.List;

@Service
public class VueloMgr {
    @Autowired
    VueloRepository vueloRepository;
    @Autowired
    AirlaneRepository airlaneRepository;
    public void addVuelo(Vuelo vuelo) throws InvalidFlightInformation {
        if (airlaneRepository==null){
            System.out.println("Todo mal");
            return;
        }
        Airline a1 = airlaneRepository.findOneByIATA(vuelo.getIATAAerolinea());
        Airline a3= airlaneRepository.findOneByIATA("LA");
        Airline a2 = airlaneRepository.findOneByICAO(vuelo.getICAO());
        System.out.println(a1.ICAO);


        if (!airlaneRepository.existsByIATAAndICAO(vuelo.getIATAAerolinea(),vuelo.getICAO())) {
            StringBuilder errorMessage = new StringBuilder("Error adding flight. ");


            throw new InvalidFlightInformation(errorMessage.toString());}

        else{
                vueloRepository.save(vuelo);
            }
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
