package um.edu.uy.business.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;


@Entity
@Table(name = "airlane")
public class Airlane {
    @Id
    @GeneratedValue(generator="airlane_ids")
    @GenericGenerator(name="airlane_ids", strategy = "increment")
    public long id;
    public String name;

    public Airlane(long id, String name) {
        this.id = id;
        this.name = name;
    }
    public Airlane(String name){
        this.name=name;
    }

    public Airlane() {

    }
}
