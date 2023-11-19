package um.edu.uy.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import um.edu.uy.DTO.PassengerRequest;
import um.edu.uy.business.PassengerMgr;
import um.edu.uy.business.entities.Passenger;
import um.edu.uy.ui.Controllers.PassengerController;

@RestController
@RequestMapping("/rest/api/passengers")
public class PassengerRestController {

    @Autowired
    private PassengerController passengerController;
    @Autowired
    private PassengerMgr passengerMgr;

    @GetMapping("/get/{passport}")
    public boolean existsPassenger(@PathVariable("passport") String passport) {
        return passengerController.existsPassenger(passport);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addPassenger(@RequestBody PassengerRequest passengerRequest) {
        try {
            Passenger passenger= new Passenger(passengerRequest.getPassport(),passengerRequest.getNacionalidad(), passengerRequest.getNombre(), passengerRequest.getApellido(), passengerRequest.getMail());
            System.out.println("Llego aca");
            passengerMgr.addPassenger(passenger);
            return ResponseEntity.ok("Passenger added successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding passenger: " + e.getMessage());
        }
    }

}

