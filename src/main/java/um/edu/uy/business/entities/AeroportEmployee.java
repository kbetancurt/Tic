package um.edu.uy.business.entities;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;


@Entity

@Table(name = "clients")
public class AeroportEmployee {

    @Id
    @GeneratedValue(generator="clients_ids")
    @GenericGenerator(name="clients_ids", strategy = "increment")
    public long id;

    public String passport;
    public String nationality;
    public Date birthDate;
    public String mail;

    public String role;
    public String name;
    public String lastName;

    public String address;
    public String password;

    public AeroportEmployee() {
    }

    public AeroportEmployee(long id, String passport, String nationality, Date birthDate, String name, String lastName, String address,String role) {
        this.id = id;
        this.passport = passport;
        this.nationality = nationality;
        this.birthDate = birthDate;
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.role=role;
        this.password=passport;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }
}
