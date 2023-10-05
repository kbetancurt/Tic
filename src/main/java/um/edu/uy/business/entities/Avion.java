package um.edu.uy.business.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "avion")

public class Avion {

    @Id
    @GeneratedValue (generator="avion_ids")
    @GenericGenerator(name="avion_ids", strategy = "increment")
    public long Id;
    public int Max_passengers;
    public int Max_weight;
    public String Model;
    public String ICAO;
    public long Id_aerolinea;

    public Avion(long id, int max_passengers, int max_weight, String model, String icao, long id_aerolinea) {
        this.Id = id;
        this.Max_passengers = max_passengers;
        this.Max_weight = max_weight;
        this.Model = model;
        this.ICAO = icao;
        this.Id_aerolinea = id_aerolinea;
    }

    public Avion( int max_passengers, int max_weight, String model, String icao, long id_aerolinea) {
        this.Max_passengers = max_passengers;
        this.Max_weight = max_weight;
        this.Model = model;
        this.ICAO = icao;
        this.Id_aerolinea = id_aerolinea;
    }

    public Avion() {

    }

    public long getId() {
        return Id;
    }
    public long setId(long id) {
        this.Id = id;
        return id;
    }

    public int getMax_passengers() {
        return Max_passengers;
    }

    public void setMax_passengers(int max_passengers) {
        this.Max_passengers = max_passengers;
    }

    public int getMax_weight() {
        return Max_weight;
    }

    public void setMax_weight(int max_weight) {
        this.Max_weight = max_weight;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        this.Model = model;
    }

    public String getICAO() {
        return ICAO;
    }
    
    public void setICAO(String icao) {
        this.ICAO = icao;
    }
}
