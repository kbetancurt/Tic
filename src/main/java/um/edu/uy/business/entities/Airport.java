package um.edu.uy.business.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;


@Entity
@Table(name="airport")
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

    public long getICAO() {
        return ICAO;
    }

    public void setICAO(long ICAO) {
        this.ICAO = ICAO;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIATA() {
        return IATA;
    }

    public void setIATA(String IATA) {
        this.IATA = IATA;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<Gates> getGates() {
        return gates;
    }

    public void setGates(List<Gates> gates) {
        this.gates = gates;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }
}
