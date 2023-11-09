package um.edu.uy.business.entities;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
@Getter
@Setter
@Entity
@Table(name = "passenger_flight")
public class PassengerFlight{

    @Id
    @GeneratedValue(generator="passenger_flights_ids")
    @GenericGenerator(name="passenger_flights_ids", strategy = "increment")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "vuelo_id")
    private Vuelo vuelo;

    @ManyToOne
    @JoinColumn(name = "passenger_id")
    private Passenger passenger;

    private boolean checkIn=false;
    private int maletas=0;

    public PassengerFlight(){}
    public PassengerFlight(Passenger passenger,Vuelo vuelo){
        this.passenger=passenger;
        this.vuelo=vuelo;
    }

}

