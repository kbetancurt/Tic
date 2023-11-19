package um.edu.uy.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import um.edu.uy.Session;
import um.edu.uy.business.entities.Airline;
import um.edu.uy.business.entities.Vuelo;
import um.edu.uy.business.exceptions.InvalidFlightInformation;
import um.edu.uy.persistence.AirlineRepository;
import um.edu.uy.persistence.VueloRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class VueloMgr {
    @Autowired
    VueloRepository vueloRepository;
    @Autowired
    AirlineRepository airlineRepository;
    public void addVuelo(Vuelo vuelo) throws InvalidFlightInformation {
        if (airlineRepository == null) {
            System.out.println("Todo mal");
            return;
        }
        if (vuelo.getAeropuertoOrigen() == null || vuelo.getAeropuertoDestino() == null || vuelo.getMatricula() == null || vuelo.getHorarioSalidaEst() == null || vuelo.getHorarioLLegadaEst() == null) {
            StringBuilder errorMessage = new StringBuilder("Error adding flight. Missing information");

            throw new InvalidFlightInformation(errorMessage.toString());
        }
        if (vuelo.getHorarioSalidaEst().isAfter(vuelo.getHorarioLLegadaEst())) {
            StringBuilder errorMessage = new StringBuilder("Error adding flight. The estimated arrival time is before the estimated departure time");

            throw new InvalidFlightInformation(errorMessage.toString());
        }
        if (vuelo.getHorarioSalidaEst().isBefore(java.time.LocalDateTime.now())) {
            StringBuilder errorMessage = new StringBuilder("Error adding flight. The estimated departure time is before the current time");

            throw new InvalidFlightInformation(errorMessage.toString());
        }
        if (vuelo.getHorarioLLegadaEst().isBefore(java.time.LocalDateTime.now())) {
            StringBuilder errorMessage = new StringBuilder("Error adding flight. The estimated arrival time is before the current time");

            throw new InvalidFlightInformation(errorMessage.toString());
        }

        else {
            vueloRepository.save(vuelo);
        }




    }
    public void updatePesoVuelo(Vuelo vuelo, int peso){
        vuelo.setBultosAcumulados(vuelo.getBultosAcumulados()+peso);
        vueloRepository.save(vuelo);
    }

    public List<Vuelo> obtenerVuelos(){
        return (List<Vuelo>) vueloRepository.findAll();

    }
    public List<Vuelo> obtenerVuelosAerolinea(){
        long id= (Session.getInstance().getAirline());
        return obtenerVuelosAerolinea(id);
    }
    public Vuelo getVueloAerolinea(Long numero){
        return vueloRepository.findOneByNumero(numero);
    }

    public List<Long> numerosVuelos(){
        List<Long>flightNumbers = new ArrayList<>();
        List<Vuelo> vuelos = (List<Vuelo>) vueloRepository.findAll();
        vuelos.forEach(flight -> { flightNumbers.add(flight.getNumero());});
        return flightNumbers;

    }

    public List<Vuelo> obtenerVuelosAerolinea(long idAerolinea){
        String aerolinea= airlineRepository.findOneById(idAerolinea).getICAO();
        List<String> vuelos = new ArrayList<>();
        List<Vuelo> vuelos1 = (List<Vuelo>) vueloRepository.findAllByICAOaerolinea(aerolinea);
        vuelos1.forEach(vuelo -> {vuelos.add(vuelo.toString());});
        return vuelos1;}

    public Integer peso_disponible(Vuelo vuelo){
        return vuelo.getBultos()-vuelo.getBultosAcumulados();
    }






}
