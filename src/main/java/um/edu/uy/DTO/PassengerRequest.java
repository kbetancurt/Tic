package um.edu.uy.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class PassengerRequest {
        private String passport;
        private String nombre;
        private String apellido;
        private String nacionalidad;
        private String mail;

        public PassengerRequest() {
        }

    }



