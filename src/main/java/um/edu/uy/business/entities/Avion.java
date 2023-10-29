package um.edu.uy.business.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "avion")

public class Avion {
    @Id
    @GeneratedValue (generator="avion_ids")
    @GenericGenerator(name="avion_ids", strategy = "increment")
    public long id;
    public int Max_passengers;
    public int Max_weight;
    public String Model;
    public String Matricula;
    public long Id_aerolinea;



    public Avion( int max_passengers, int max_weight, String model, String matricula, long id_aerolinea) {
        this.Max_passengers = max_passengers;
        this.Max_weight = max_weight;
        this.Model = model;
        this.Matricula = matricula;
        this.Id_aerolinea = id_aerolinea;
    }

    public Avion() {

    }


}
