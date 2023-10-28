package um.edu.uy.business.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;


@Getter
@Setter
@Entity
@Table(name = "airport")
public class Airport {
    @Id
    public long ICAO;

    public String name;
    public String IATA;
    public String country;

    @OneToMany(mappedBy = "airport")
    public List<Gates> gates;

    @OneToMany(mappedBy = "airport")
    public List<Track> tracks;


}
