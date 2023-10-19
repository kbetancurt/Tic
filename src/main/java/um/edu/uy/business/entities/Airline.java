package um.edu.uy.business.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "airline")
public class Airline {
    @Id
    @GeneratedValue(generator="airline_ids")
    @GenericGenerator(name="airline_ids", strategy = "increment")
    public long id;
    public String name;
    public String IATA;
    public String ICAO;
    public String pais;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIATA() {
        return IATA;
    }

    public void setIATA(String IATA) {
        this.IATA = IATA;
    }

    public String getICA0() {
        return ICAO;
    }

    public void setICA0(String ICA0) {
        this.ICAO = ICA0;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public Airline(long id, String name, String IATA, String ICAO, String pais) {
        this.id = id;
        this.name = name;
        this.IATA=IATA;
        this.ICAO=ICAO;
        this.pais=pais;

    }
    public Airline(String name, String IATA, String ICAO, String pais){
        this.name=name;
        this.IATA=IATA;
        this.ICAO=ICAO;
        this.pais=pais;
    }

    public Airline() {

    }
}
