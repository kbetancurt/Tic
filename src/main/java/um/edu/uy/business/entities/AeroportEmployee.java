package um.edu.uy.business.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;


@Getter
@Setter
@Entity
@Table(name = "aeroport_employees", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"mail"})
})
public class AeroportEmployee {

    @Id
    @GeneratedValue(generator="aeroport_employees_ids")
    @GenericGenerator(name="aeroport_employees_ids", strategy = "increment")
    public long id_employee;

    public String passport;
    public String nationality;
    public Date birthDate;
    public String mail;

    public String role;
    public String name;
    public String lastName;

    public String address;
    public String password;

    @ManyToOne
    @JoinColumn(name="airport")
    public Airport airport;
    @ManyToOne
    @JoinColumn (name="airline")
    public Airline airline;

    public AeroportEmployee() {
    }

    public AeroportEmployee(String passport, String nationality, Date birthDate, String name, String lastName, String address,String role,Airport airport,String mail) {
        this.passport = passport;
        this.nationality = nationality;
        this.birthDate = birthDate;
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.role=role;
        this.password=passport;
        this.airport=airport;
        this.mail=mail;
    }
    public AeroportEmployee(String passport, String nationality, Date birthDate, String name, String lastName, String address,String role,Airport airport,String mail,Airline airline) {
        this.passport = passport;
        this.nationality = nationality;
        this.birthDate = birthDate;
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.role=role;
        this.password=passport;
        this.airport=airport;
        this.mail=mail;
        this.airline=airline;
    }

    @JoinColumn(name="airport")
    public Airport getAirport() {
        return airport;
    }
    @JoinColumn(name="airline")
    public Airline getAirline() {
        return airline;
    }
}
