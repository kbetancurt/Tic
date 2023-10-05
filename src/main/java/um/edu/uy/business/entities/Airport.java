package um.edu.uy.business.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;


@Entity
@Table(name="airport")
public class Airport {
    @Id
    @GeneratedValue(generator="airport_ids")
    @GenericGenerator(name="airport_ids", strategy = "increment")
    public long id;
    public String name;
    public String IATA;

}
