package um.edu.uy.business;

import javafx.collections.ObservableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import um.edu.uy.business.entities.Airline;
import um.edu.uy.business.entities.Vuelo;
import um.edu.uy.business.exceptions.InvalidFlightInformation;
import um.edu.uy.persistence.AirlaneRepository;
import um.edu.uy.persistence.VueloRepository;

import java.util.ArrayList;
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

    public List<Vuelo> obtenerVuelos(){
        return (List<Vuelo>) vueloRepository.findAll();

    }

    public List<String> obtenerIATA(){
        List<String>IATAs = new ArrayList<>();
        List<Vuelo> vuelos = (List<Vuelo>) vueloRepository.findAll();
        vuelos.forEach(vuelo -> { IATAs.add(vuelo.getIATAAerolinea());});
        return IATAs;
    }

public List<String> obtenerICAO(){
        List<String>ICAOS = new ArrayList<>();
        List<Vuelo> vuelos = (List<Vuelo>) vueloRepository.findAll();
        vuelos.forEach(vuelo -> { ICAOS.add(vuelo.getICAO());});
        return ICAOS;
    }

    public List<String> obtenerAeropuertosOrigen(){
        List<String>aeropuertosOrigen = new ArrayList<>();
        List<Vuelo> vuelos = (List<Vuelo>) vueloRepository.findAll();
        vuelos.forEach(vuelo -> { aeropuertosOrigen.add(vuelo.getAeropuertoOrigen());});
        return aeropuertosOrigen;
    }

    public List<String> obtenerAeropuertosDestino(){
        List<String>aeropuertosDestino = new ArrayList<>();
        List<Vuelo> vuelos = (List<Vuelo>) vueloRepository.findAll();
        vuelos.forEach(vuelo -> { aeropuertosDestino.add(vuelo.getAeropuertoDestino());});
        return aeropuertosDestino;
    }

    public List<String> obtenerMatriculas(){
        List<String>matriculas = new ArrayList<>();
        List<Vuelo> vuelos = (List<Vuelo>) vueloRepository.findAll();
        vuelos.forEach(vuelo -> { matriculas.add(vuelo.getMatricula());});
        return matriculas;
    }

    public List<String> obtenerEstados(){
        List<String>estados = new ArrayList<>();
        List<Vuelo> vuelos = (List<Vuelo>) vueloRepository.findAll();
        vuelos.forEach(vuelo -> { estados.add(vuelo.getEstado());});
        return estados;
    }

    public List<String> obtenerHorariosSalidaEst(){
        List<String>horariosSalidaEst = new ArrayList<>();
        List<Vuelo> vuelos = (List<Vuelo>) vueloRepository.findAll();
        vuelos.forEach(vuelo -> { horariosSalidaEst.add(vuelo.getHorarioSalidaEst().toString());});
        return horariosSalidaEst;
    }

    public List<String> obtenerHorariosLLegadaEst(){
        List<String>horariosLLegadaEst = new ArrayList<>();
        List<Vuelo> vuelos = (List<Vuelo>) vueloRepository.findAll();
        vuelos.forEach(vuelo -> { horariosLLegadaEst.add(vuelo.getHorarioLLegadaEst().toString());});
        return horariosLLegadaEst;
    }


}
