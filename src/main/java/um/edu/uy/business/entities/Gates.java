package um.edu.uy.business.entities;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


@Entity
@Table(name="gates")
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
