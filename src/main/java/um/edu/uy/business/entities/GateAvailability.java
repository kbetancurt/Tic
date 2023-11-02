package um.edu.uy.business.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "gate_availability")

public class GateAvailability {
    @Id
    @GeneratedValue(generator="gates_availability_ids")
    public long Id;
    @ManyToOne
    @JoinColumn(name="gate_id")
    public Gates gate;
    public LocalDateTime startOccupation;
    public LocalDateTime endOccupation;

    public GateAvailability() {
    }

    public GateAvailability(Gates gate, LocalDateTime startOccupation, LocalDateTime endOccupation) {
        this.gate = gate;
        this.startOccupation = startOccupation;
        this.endOccupation = endOccupation;
    }
}

