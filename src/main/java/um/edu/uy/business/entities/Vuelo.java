package um.edu.uy.business.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Time;
import java.time.LocalDateTime;

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

    public String IATAAerolinea;
    public String ICAO;
    public String aeropuertoOrigen;
    public String aeropuertoDestino;
    public String matricula;
    public Integer asientos;
    public Integer bultos;
    public LocalDateTime horarioSalidaEst;
    public LocalDateTime horarioLLegadaEst;
    public LocalDateTime horariosSalidaRea;
    public LocalDateTime horarioLLegadaReal;
    public boolean aprobadoSalida;
    public boolean aprobadoLLegada;
    public String estado;

    public Vuelo(long id, long numero, String IATAAerolinea, String ICAO, String aeropuertoOrigen, String aeropuertoDestino, String matricula, Integer asientos, Integer bultos, LocalDateTime horarioSalidaEst, LocalDateTime horarioLLegadaEst) {
        this.id = id;
        this.numero = numero;
        this.IATAAerolinea = IATAAerolinea;
        this.ICAO = ICAO;
        this.aeropuertoOrigen = aeropuertoOrigen;
        this.aeropuertoDestino = aeropuertoDestino;
        this.matricula = matricula;
        this.asientos = asientos;
        this.bultos = bultos;
        this.horarioSalidaEst = horarioSalidaEst;
        this.horarioLLegadaEst = horarioLLegadaEst;
        this.aprobadoSalida=false;
        this.aprobadoLLegada=false;
    }


    public Vuelo() {

    }

    public boolean getAprobadoSalida() {
        return aprobadoSalida;
    }

    public boolean getAprobadoLLegada() {
        return aprobadoLLegada;
    }
}
