package um.edu.uy.business.entities;

import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;

import java.util.List;


@Getter
@Setter
@Entity
@Table(name = "airport")
public class Airport {
    @Id
    @GeneratedValue(generator="airport_ids")
    public long Id;

    public String ICAO;

    public String name;

    public String IATA;
    public String country;

    @OneToMany(mappedBy = "airport")
    public List<Gates> gates;

    @OneToMany(mappedBy = "airport")
    public List<Track> tracks;


}
