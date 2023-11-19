package um.edu.uy.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import um.edu.uy.DTO.PassengerRequest;
import um.edu.uy.business.PassengerFlightMgr;
import um.edu.uy.business.entities.Passenger;
import um.edu.uy.business.entities.Vuelo;

@RestController
@RequestMapping("/rest/api/passengerflights")
public class PassengerFlightRestController {
    @Autowired
    PassengerFlightMgr passengerFlightMgr;

    @PostMapping("/add")
    public ResponseEntity<String> addPassengerFlight(@RequestBody PassengerRequest passengerRequest, Vuelo vuelo) {
        try {
            Passenger passenger= new Passenger(passengerRequest.getPassport(),passengerRequest.getNacionalidad(), passengerRequest.getNombre(), passengerRequest.getApellido(), passengerRequest.getMail());
            System.out.println("Llego aca");
            passengerFlightMgr.addPassengerFlight(passenger,vuelo);
            return ResponseEntity.ok("Passenger added successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding passenger: " + e.getMessage());
        }
    }


}
