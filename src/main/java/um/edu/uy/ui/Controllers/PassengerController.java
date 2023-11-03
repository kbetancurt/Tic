package um.edu.uy.ui.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import um.edu.uy.business.AirlaneMgr;
import um.edu.uy.business.AvionMgr;
import um.edu.uy.business.PassengerMgr;
import um.edu.uy.business.VueloMgr;
import um.edu.uy.Session;
import um.edu.uy.persistence.AirlineRepository;

import java.awt.*;

@Component
public class PassengerController {
    @Autowired
    PassengerMgr passengerMgr;
    @Autowired
    VueloMgr vueloMgr;
    @Autowired
    AirlaneMgr airlaneMgr;
    @Autowired
    AirlineRepository airlineRepository;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtApellido;
    @FXML
    private TextField txtNacionalidad;
    @FXML
    private TextField txtPasaporte;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnClose;
    @FXML
    private ChoiceBox<String> choiceBoxFlights;
    @FXML
    void initialize(){
        if (airlineRepository==null){
            System.out.println("error");
            return;
        }
        long aerolinea = Session.getInstance().getAirline();
        System.out.println(aerolinea);
        if (airlineRepository.findOneById(aerolinea)==null){
            System.out.println("error");
            return;
        }
        String ICA0= airlineRepository.findOneById(aerolinea).getICAO();
        if (vueloMgr.obtenerVuelosAerolinea(ICA0)==null){
            System.out.println("No hay vuelos disponibles");
            return;
        }
        choiceBoxFlights.getItems().addAll(vueloMgr.obtenerVuelosAerolinea(ICA0).toString());
    }

    public boolean existsPassenger(String passport) {
        return passengerMgr.existsPassenger(passport);
    }
    public String generateMail(String nombre, String apellido) {
        return passengerMgr.generateMail(nombre,apellido);
    }
    @FXML
    void close(ActionEvent actionEvent) {
        Node source = (Node)  actionEvent.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }


}
