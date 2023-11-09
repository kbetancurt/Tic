package um.edu.uy.business.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "Runway")
public class Runway {
    @Id
    @GeneratedValue(generator="runway_ids")
    @GenericGenerator(name="runway_ids", strategy = "increment")

    public long id;

    public String name;

    public long max_weight;

    @ManyToOne
    @JoinColumn(name="airport_id")
    private Airport airport;

}