package um.edu.uy.business.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;


@Entity
@Table(name = "aeroport_employees")
public class AeroportEmployee {

    @Id
    @GeneratedValue(generator="aeroport_employees_ids")
    @GenericGenerator(name="aeroport_employees_ids", strategy = "increment")
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
    public String airport;


    public AeroportEmployee() {
    }

    public AeroportEmployee(String passport, String nationality, Date birthDate, String name, String lastName, String address,String role,String airport) {
        this.passport = passport;
        this.nationality = nationality;
        this.birthDate = birthDate;
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.mail= generateEmail(name,lastName,airport);
        this.role=role;
        this.password=passport;
        this.airport=airport;
    }
    public String generateEmail(String name,String lastName, String airport){
        //hay que ver que chequee que no exista el email y generarle uno nuevo con un numero en ese caso ej nsere1@airportemail.com
        return Character.toLowerCase(name.charAt(0))+lastName.toLowerCase()+"@"+airport.toLowerCase()+".com";}
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
    public void setPassword(String password){this.password=password;}
}
