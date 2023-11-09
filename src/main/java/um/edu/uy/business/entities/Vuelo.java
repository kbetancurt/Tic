package um.edu.uy.business.entities;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "flight")
public class Vuelo {
    @Id
    @GeneratedValue(generator="vuelo_ids")
    @GenericGenerator(name="vuelo_ids", strategy = "increment")
    public long id;
    public long numero;
    public String ICAOaerolinea;
    public String IATAaerolinea;
    public String aeropuertoOrigen;
    public String aeropuertoDestino;
    public String matricula;
    public Integer asientos;
    public Integer asientosDisponibles;
    public Integer bultos;
    public LocalDateTime horarioSalidaEst;
    public LocalDateTime horarioLLegadaEst;
    public LocalDateTime horariosSalidaReal;
    public LocalDateTime horarioLLegadaReal;
    public boolean aprobadoSalida;
    public boolean aprobadoLLegada;
    public String estado;
    @ManyToMany
    @JoinTable(
            name = "passenger_flight",
            joinColumns = @JoinColumn(name = "vuelo_id"),
            inverseJoinColumns = @JoinColumn(name = "passenger_id")
    )
    public List<Passenger> passengerList= new ArrayList<>();



    public Vuelo(long numero, String ICAOaerolinea, String IATAaerolinea, String aeropuertoOrigen, String aeropuertoDestino, String matricula, Integer asientos, Integer bultos, LocalDateTime horarioSalidaEst, LocalDateTime horarioLLegadaEst) {
        this.numero = numero;
        this.ICAOaerolinea = ICAOaerolinea;
        this.IATAaerolinea = IATAaerolinea;
        this.aeropuertoOrigen = aeropuertoOrigen;
        this.aeropuertoDestino = aeropuertoDestino;
        this.matricula = matricula;
        this.asientos = asientos;
        this.bultos = bultos;
        this.horarioSalidaEst = horarioSalidaEst;
        this.horarioLLegadaEst = horarioLLegadaEst;
        this.aprobadoSalida = false;
        this.aprobadoLLegada = false;
        this.estado = "pendiente";
        this.asientosDisponibles=asientos;
    }

    public boolean getAprobadoSalida() {
        return aprobadoSalida;
    }

    public boolean getAprobadoLLegada() {
        return aprobadoLLegada;
    }




}
