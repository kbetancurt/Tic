package um.edu.uy.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import um.edu.uy.business.entities.AeroportEmployee;
import um.edu.uy.business.exceptions.AirportEmployeeAlreadyExists;
import um.edu.uy.business.exceptions.InvalidAirportEmployeeInformation;
import um.edu.uy.persistence.AeroportEmployeeRepository;

@Service
public class AeroportEmployeeMgr {

    public AeroportEmployeeRepository getAeroportEmployeeRepository() {
        return aeroportEmployeeRepository;
    }

    public void setAeroportEmployeeRepository(AeroportEmployeeRepository aeroportEmployeeRepository) {
        this.aeroportEmployeeRepository = aeroportEmployeeRepository;
    }

    @Autowired
    private AeroportEmployeeRepository aeroportEmployeeRepository;

    public void addClient(AeroportEmployee aeroportEmployee)
            throws InvalidAirportEmployeeInformation, AirportEmployeeAlreadyExists {

        if (aeroportEmployee.getName() == null || "".equals(aeroportEmployee.getName()) || aeroportEmployee.getAddress() == null || "".equals(aeroportEmployee.getAddress())) {

            throw new InvalidAirportEmployeeInformation("Alguno de los datos ingresados no es correcto");

        }

        // Verifico si el cliente no existe

        if (aeroportEmployeeRepository.findOneByPassport(aeroportEmployee.getPassport()) != null) {

            throw new AirportEmployeeAlreadyExists();
        }

        aeroportEmployeeRepository.save(aeroportEmployee);

    }

    public AeroportEmployee getAirportEmployee(String mail){
        return aeroportEmployeeRepository.findOneByMail(mail);
    }
    public void updatePassword(AeroportEmployee employee,String password){
        employee.setPassword(password);
        aeroportEmployeeRepository.save(employee);
    }

    public String GenerateMail(String name,String lastName, String airport) {
        //hay que ver que chequee que no exista el email y generarle uno nuevo con un numero en ese caso ej
        String newmail = Character.toLowerCase(name.charAt(0)) + lastName.toLowerCase() + "@" + airport.toLowerCase() + ".com";
        if (aeroportEmployeeRepository.findOneByMail(newmail) != null) {
            int i = 1;
            while (aeroportEmployeeRepository.findOneByMail(newmail) != null) {
                newmail = Character.toLowerCase(name.charAt(0)) + lastName.toLowerCase() + i + "@" + airport.toLowerCase() + ".com";
                i++;

            }
        }
        return newmail;
    }
    public String generateMailAirline(String name, String lastName, String airline) {
        //hay que ver que chequee que no exista el email y generarle uno nuevo con un numero en ese caso ej
        String newmail = Character.toLowerCase(name.charAt(0)) + lastName.toLowerCase() + "@" + airline.toLowerCase() + ".com";
        if (aeroportEmployeeRepository.findOneByMail(newmail) != null) {
            int i = 1;
            while (aeroportEmployeeRepository.findOneByMail(newmail) != null) {
                newmail = Character.toLowerCase(name.charAt(0)) + lastName.toLowerCase() + i + "@" + airline.toLowerCase() + ".com";
                i++;

            }
        }
        return newmail;
    }



}
