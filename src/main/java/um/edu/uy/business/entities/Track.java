package um.edu.uy.business.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


@Entity
@Table(name="track")
public class Track {
    @Id
    @GeneratedValue(generator="track_ids")
    @GenericGenerator(name="track_ids", strategy = "increment")

    public long id;

    public String name;

    public String cat_max;

    @ManyToOne
    @JoinColumn(name="airport_id")
    private Airport airport;

}