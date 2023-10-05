package um.edu.uy.business.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;


@Entity
@Table(name = "airlane")
public class Airlane {
    @Id
    @GeneratedValue(generator="airlane_ids")
    @GenericGenerator(name="airlane_ids", strategy = "increment")
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

    public Airlane(long id, String name, String IATA, String ICAO, String pais) {
        this.id = id;
        this.name = name;
        this.IATA=IATA;
        this.ICAO=ICAO;
        this.pais=pais;

    }
    public Airlane(String name,String IATA, String ICAO, String pais){
        this.name=name;
        this.IATA=IATA;
        this.ICAO=ICAO;
        this.pais=pais;
    }

    public Airlane() {

    }
}
