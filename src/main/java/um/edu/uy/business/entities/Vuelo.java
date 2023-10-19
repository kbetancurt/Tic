package um.edu.uy.business.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Time;

@Entity
@Table(name="flight")
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
    public String asientos;
    public String bultos;
    public Time horarioSalidaEst;
    public Time horarioLLegadaEst;
    public Time horariosSalidaRea;
    public Time horarioLLegadaReal;
    public boolean aprobadoSalida;
    public boolean aprobadoLLegada;
    public String estado;

    public Vuelo(long id, long numero, String IATAAerolinea, String ICAO, String aeropuertoOrigen, String aeropuertoDestino, String matricula, String asientos, String bultos, Time horarioSalidaEst, Time horarioLLegadaEst) {
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getNumero() {
        return numero;
    }

    public void setNumero(long numero) {
        this.numero = numero;
    }

    public String getIATAAerolinea() {
        return IATAAerolinea;
    }

    public void setIATAAerolinea(String IATAAerolinea) {
        this.IATAAerolinea = IATAAerolinea;
    }

    public String getICAO() {
        return ICAO;
    }

    public void setICAO(String ICAO) {
        this.ICAO = ICAO;
    }

    public String getAeropuertoOrigen() {
        return aeropuertoOrigen;
    }

    public void setAeropuertoOrigen(String aeropuertoOrigen) {
        this.aeropuertoOrigen = aeropuertoOrigen;
    }

    public String getAeropuertoDestino() {
        return aeropuertoDestino;
    }

    public void setAeropuertoDestino(String aeropuertoDestino) {
        this.aeropuertoDestino = aeropuertoDestino;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getAsientos() {
        return asientos;
    }

    public void setAsientos(String asientos) {
        this.asientos = asientos;
    }

    public String getBultos() {
        return bultos;
    }

    public void setBultos(String bultos) {
        this.bultos = bultos;
    }

    public Time getHorarioSalidaEst() {
        return horarioSalidaEst;
    }

    public void setHorarioSalidaEst(Time horarioSalidaEst) {
        this.horarioSalidaEst = horarioSalidaEst;
    }

    public Time getHorarioLLegadaEst() {
        return horarioLLegadaEst;
    }

    public void setHorarioLLegadaEst(Time horarioLLegadaEst) {
        this.horarioLLegadaEst = horarioLLegadaEst;
    }

    public Time getHorariosSalidaRea() {
        return horariosSalidaRea;
    }

    public void setHorariosSalidaRea(Time horariosSalidaRea) {
        this.horariosSalidaRea = horariosSalidaRea;
    }

    public Time getHorarioLLegadaReal() {
        return horarioLLegadaReal;
    }

    public void setHorarioLLegadaReal(Time horarioLLegadaReal) {
        this.horarioLLegadaReal = horarioLLegadaReal;
    }

    public boolean isAprobadoLLegada() {
        return aprobadoLLegada;
    }

    public void setAprobadoLLegada(boolean aprobado) {
        this.aprobadoLLegada = aprobado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
