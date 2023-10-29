package um.edu.uy.business.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Time;
import java.time.LocalDateTime;


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
    public Integer bultos;
    public LocalDateTime horarioSalidaEst;
    public LocalDateTime horarioLLegadaEst;
    public LocalDateTime horariosSalidaReal;
    public LocalDateTime horarioLLegadaReal;
    public boolean aprobadoSalida;
    public boolean aprobadoLLegada;
    public String estado;

    public Vuelo(long numero, String ICAOaerolinea, String IATAaerolinea, String aeropuertoOrigen, String aeropuertoDestino, String matricula, Integer asientos, Integer bultos, LocalDateTime horarioSalidaEst, LocalDateTime horarioLLegadaEst, LocalDateTime horariosSalidaReal, LocalDateTime horarioLLegadaReal, boolean aprobadoSalida, boolean aprobadoLLegada, String estado) {
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
        this.horariosSalidaReal = horariosSalidaReal;
        this.horarioLLegadaReal = horarioLLegadaReal;
        this.aprobadoSalida = aprobadoSalida;
        this.aprobadoLLegada = aprobadoLLegada;
        this.estado = estado;
    }

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

    }

    public boolean getAprobadoSalida() {
        return aprobadoSalida;
    }

    public boolean getAprobadoLLegada() {
        return aprobadoLLegada;
    }




}
