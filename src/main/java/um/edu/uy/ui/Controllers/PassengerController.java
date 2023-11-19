package um.edu.uy.ui.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import um.edu.uy.DTO.PassengerRequest;
import um.edu.uy.business.AirlaneMgr;
import um.edu.uy.business.PassengerFlightMgr;
import um.edu.uy.business.PassengerMgr;
import um.edu.uy.business.VueloMgr;
import um.edu.uy.Session;
import um.edu.uy.business.entities.Passenger;
import um.edu.uy.business.entities.Vuelo;
import um.edu.uy.persistence.AirlineRepository;
import um.edu.uy.persistence.VueloRepository;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TextField;
import um.edu.uy.restcontrollers.PassengerRestController;

import java.awt.*;

@Component
public class PassengerController {
    @Autowired
    PassengerMgr passengerMgr;
    @Autowired
    PassengerRestController passengerRestController;
    @Autowired
    VueloMgr vueloMgr;
    @Autowired
    AirlaneMgr airlaneMgr;
    @Autowired
    AirlineRepository airlineRepository;
    @Autowired
    VueloRepository vueloRepository;
    @Autowired
    PassengerFlightMgr passengerFlightMgr;
    @FXML
    private TableView<Vuelo> flights;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtApellido;
    @FXML
    private TextField txtNacionalidad;
    @FXML
    private TextField txtPasaporte;
    @FXML
    private javafx.scene.control.Button btnAdd;
    @FXML
    private javafx.scene.control.Button btnClose;
    @FXML
    private TableColumn<Vuelo, Integer> flightNumber;

    @FXML
    private TableColumn<Vuelo,String> destinationAirport;
    @FXML
    private TableColumn<Vuelo,String> originAirport;
    @FXML
    void initialize(){


        flightNumber.setCellValueFactory(new PropertyValueFactory<>("numero"));
        destinationAirport.setCellValueFactory(new PropertyValueFactory<>("aeropuertoDestino"));
        originAirport.setCellValueFactory(new PropertyValueFactory<>("aeropuertoOrigen"));
        for (Vuelo vuelo : vueloMgr.obtenerVuelosAerolinea()) {
                flights.getItems().add(vuelo);
            }
        }


    public boolean existsPassenger(String passport) {
        return passengerMgr.existsPassenger(passport);
    }

    public String generateMail(String nombre, String apellido) {
        return passengerMgr.generateMail(nombre,apellido);
    }
    public void addPassenger(){
        String passport= txtPasaporte.getText();
        String apellido= txtApellido.getText();
        String nombre= txtNombre.getText();
        String nacionalidad= txtNacionalidad.getText();
        Vuelo flight = flights.getSelectionModel().getSelectedItem();
        if (!existsPassenger(passport)){

            String mail= generateMail(nombre,apellido);
            Passenger passenger = new Passenger(passport,nacionalidad,nombre,apellido,mail);
            PassengerRequest passengerRequest = new PassengerRequest();
            passengerRequest.setApellido(apellido);
            passengerRequest.setNacionalidad(nacionalidad);
            passengerRequest.setNombre(nombre);
            passengerRequest.setPassport(passport);
            passengerRequest.setMail(mail);
            passengerRestController.addPassenger(passengerRequest);
            //passengerFlightMgr.addPassengerFlight(passenger,flight);
            showAlert("Agregado exitosamente","Se agrego con exito el pasajero");
        }
        else
        {
            Passenger passenger = passengerMgr.getPassenger(passport);
            passengerFlightMgr.addPassengerFlight(passenger,flight);
            showAlert("Agregado exitosamente","Se agrego con exito el pasajero");

        }


    }
    @FXML
    void close(ActionEvent actionEvent) {
        Node source = (Node)  actionEvent.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }
    private void showAlert (String title, String contextText){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }



}
