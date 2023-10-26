package um.edu.uy.business.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


@Entity
@Table(name = "airline")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Airline {
    @Id
    @GeneratedValue(generator="airline_ids")
    @GenericGenerator(name="airline_ids", strategy = "increment")
    public long id;
    @Column(name="name")
    public String name;
    @Column(name="iata")
    public String IATA;
    @Column(name="icao")
    public String ICAO;
    public String pais;


    public Airline(String name, String IATA, String ICAO, String pais){
        this.name=name;
        this.IATA=IATA;
        this.ICAO=ICAO;
        this.pais=pais;
    }

}
