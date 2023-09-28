package um.edu.uy.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import um.edu.uy.business.entities.AeroportEmployee;
import um.edu.uy.business.exceptions.AirportEmployeeAlreadyExists;
import um.edu.uy.business.exceptions.InvalidAirportEmployeeInformation;
import um.edu.uy.persistence.AeroportEmployeeRepository;

import javax.annotation.PostConstruct;

@Service
public class AeroportEmployeeMgr {

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

    @PostConstruct
    public AeroportEmployee checkLogin (String mail){
        return aeroportEmployeeRepository.findOneByMail(mail);
    }

}
