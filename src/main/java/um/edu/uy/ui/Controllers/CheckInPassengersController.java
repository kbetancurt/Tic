package um.edu.uy.ui.Controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import um.edu.uy.Main;
import um.edu.uy.Session;
import um.edu.uy.business.*;
import um.edu.uy.business.entities.Airline;
import org.springframework.stereotype.Component;
import um.edu.uy.business.entities.Passenger;
import um.edu.uy.business.entities.PassengerFlight;
import um.edu.uy.business.entities.Vuelo;
import um.edu.uy.business.exceptions.AirlineAlreadyExists;
import um.edu.uy.business.exceptions.InvalidAirlineInformation;
import um.edu.uy.persistence.AirlineRepository;

import java.io.IOException;

@Component
public class CheckInPassengersController {
    @Autowired
    AirlineRepository airlineRepository;
    @Autowired
    AirlaneMgr airlaneMgr;
    @Autowired
    VueloMgr vueloMgr;

    @Autowired
    PassengerMgr passengerMgr;
    @Autowired
    PassengerFlightMgr passengerFlightMgr;

    @FXML
    private Button bttnCheck;

    @FXML
    private Button bttnCancel;
    @FXML
    private Button bttnPesoDisponible;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtLastName;

    @FXML
    private TextField txtPassport;
    @FXML
    private TextField txtBultos;

    @FXML
    private ChoiceBox<String> choiceBoxPesoDisponible;
    @FXML
    private TableView<Vuelo> flights;
    @FXML
    private TableColumn<Vuelo, Integer> flightNumber;

    @FXML
    private TableColumn<Vuelo,String> destinationAirport;
    @FXML
    private TableColumn<Vuelo,String> originAirport;





    @FXML
    void close(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    void initialize(){
        flightNumber.setCellValueFactory(new PropertyValueFactory<>("numero"));
        destinationAirport.setCellValueFactory(new PropertyValueFactory<>("aeropuertoDestino"));
        originAirport.setCellValueFactory(new PropertyValueFactory<>("aeropuertoOrigen"));
        for (Vuelo vuelo : vueloMgr.obtenerVuelosAerolinea()) {
            flights.getItems().add(vuelo);
        }
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
    }

    @FXML
    void verPesoDisponible(ActionEvent event){
        Vuelo flight = flights.getSelectionModel().getSelectedItem();
        Integer peso_disponible= vueloMgr.peso_disponible(flight);
        System.out.println(peso_disponible);
        choiceBoxPesoDisponible.getItems().add(peso_disponible.toString());
    }
    @FXML
    void checkInPassenger(ActionEvent event) {
        if (txtName.getText() == null || txtName.getText().isEmpty() ||
                txtLastName.getText() == null || txtLastName.getText().isEmpty()
                || txtPassport.getText() == null || txtPassport.getText().isEmpty()


        ) {

            showAlert(
                    "Datos faltantes!",
                    "No se ingresaron los datos necesarios para completar el ingreso.");

        } else {
            Vuelo flight = flights.getSelectionModel().getSelectedItem();
            String name = txtName.getText();
            String lastName= txtLastName.getText();
            String passport = txtPassport.getText();
            String bultos= txtBultos.getText();
            Integer peso_disponible= vueloMgr.peso_disponible(flight);
            Passenger passenger= passengerMgr.getPassenger(passport);
            if (flight !=null && passenger !=null){
                PassengerFlight passengerFlight= passengerFlightMgr.findPassengerFlight(passenger,flight);
                if (passengerFlight!=null){
                    if (Integer.parseInt(bultos)<=peso_disponible){
                        passengerFlight.setMaletas(Integer.parseInt(bultos));
                        if (!passengerFlight.isCheckIn()){
                        passengerFlight.setCheckIn(true);
                        passengerFlightMgr.updatePassengerFlight(passengerFlight);
                        vueloMgr.updatePesoVuelo(flight,Integer.parseInt(bultos));
                        showAlert("Check-in","Se realizo el check-in con exito");}
                        else {
                            showAlert("Error","El pasajero ya realizo el check-in");
                        }
                    }
                    else{
                        showAlert("Error","El peso de las valijas supera el peso disponible");
                    }

                }
                else{
                    showAlert("Error","El pasajero no esta registrado en el vuelo");
                }
            }
            else{
                showAlert("Error","El pasajero no esta registrado en ningún vuelo");
            }
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