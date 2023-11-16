package um.edu.uy.business.entities;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "runway_availability")
public class RunwayAvailability {
    @Id
    @GeneratedValue(generator="runway_availability_ids")
    @GenericGenerator(name="runway_availability_ids", strategy = "increment")
    private Long id;
    @ManyToOne
    @JoinColumn(name="runway_id")
    private Runway runway;

    private LocalDateTime startOccupation;
    private LocalDateTime endOccupation;

    @ManyToOne
    @JoinColumn(name="flight_id")
    private Vuelo flight;

    public RunwayAvailability(Runway runway, LocalDateTime startOccupation, LocalDateTime endOccupation, Vuelo flight) {
        this.runway = runway;
        this.startOccupation = startOccupation;
        this.endOccupation = endOccupation;
        this.flight = flight;

    }





}
