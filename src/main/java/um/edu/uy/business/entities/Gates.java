package um.edu.uy.business.entities;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


@Getter
@Setter
@Entity
@Table(name = "gates")
public class Gates {
    @Id
    @GeneratedValue(generator="gates_ids")
    @GenericGenerator(name="gates_ids", strategy = "increment")

    public long id;

    public String name;

    @ManyToOne
    @JoinColumn(name="airport_id")
    public Airport airport;

}
