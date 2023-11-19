package um.edu.uy.ui.Controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import um.edu.uy.Session;
import um.edu.uy.business.PassengerMgr;
import um.edu.uy.business.VueloMgr;
import org.springframework.stereotype.Component;
import um.edu.uy.business.entities.Vuelo;
import um.edu.uy.persistence.AirlineRepository;

@Component
public class CheckInPassengersController {
    @Autowired
    AirlineRepository airlineRepository;

    @Autowired
    PassengerMgr passengerMgr;
    @Autowired
    VueloMgr vueloMgr;

    @FXML
    private Button bttnCheck;

    @FXML
    private Button bttnCancel;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtLastName;

    @FXML
    private TextField txtPassport;

    @FXML
    private ChoiceBox<String> choiceBoxFlight;

    @FXML
    void close(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

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
        if (vueloMgr.obtenerVuelosAerolinea()==null){
            System.out.println("No hay vuelos disponibles");
            return;
        }
        choiceBoxFlight.getItems().addAll(vueloMgr.numerosVuelos().toString());
    }

    void checkPassenger(ActionEvent event) {
        if (txtName.getText() == null || txtName.getText().isEmpty() ||
                txtLastName.getText() == null || txtLastName.getText().isEmpty()
                || txtPassport.getText() == null || txtPassport.getText().isEmpty() ||
                choiceBoxFlight.getValue()==null || choiceBoxFlight.getValue().isEmpty()

        ) {

            showAlert(
                    "Datos faltantes!",
                    "No se ingresaron los datos necesarios para completar el ingreso.");

        } else {

            String name = txtName.getText();
            String lastName= txtLastName.getText();
            String passport = txtPassport.getText();
            Long flightNumber = Long.valueOf(choiceBoxFlight.getValue());
            Vuelo vuelo = vueloMgr.getVueloAerolinea(flightNumber);

            //Falta chequar que este en la tabla pasajero-vuelo y se deberia agregar el id de la valija

        }

    }

    private void showAlert(String title, String contextText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }
}