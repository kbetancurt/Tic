package um.edu.uy.business.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "passenger", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"mail"})
            ,@UniqueConstraint(columnNames = {"passport"})})
public class Passenger{
        @Id
        @GeneratedValue(generator="aeroport_employees_ids")
        @GenericGenerator(name="aeroport_employees_ids", strategy = "increment")
        public long id_employee;
        public String passport;
        public String nationality;
        public String mail;
        public String role;
        public String name;
        public String lastName;
        public String password;




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
