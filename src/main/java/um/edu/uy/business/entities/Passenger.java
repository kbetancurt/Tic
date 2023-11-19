package um.edu.uy.business.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "passenger", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"mail"})
            ,@UniqueConstraint(columnNames = {"passport"})})
public class Passenger{
        @Id
        @GeneratedValue(generator="passenger_ids")
        @GenericGenerator(name="passenger_ids", strategy = "increment")
        public long id;
        public String passport;
        public String nationality;
        public String mail;
        public String name;
        public String lastName;
        public String password;
        @OneToMany(mappedBy = "passenger")
        private List<PassengerFlight> vueloPassengers;

        public Passenger() {
        }

        public Passenger(String passport, String nationality,  String name, String lastName, String mail) {
        this.passport = passport;
        this.nationality = nationality;
        this.name = name;
        this.lastName = lastName;
        this.password=passport;
        this.mail=mail;
        }



}
