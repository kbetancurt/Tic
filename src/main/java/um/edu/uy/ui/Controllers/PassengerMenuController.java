package um.edu.uy.ui.Controllers;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import um.edu.uy.Main;
import um.edu.uy.Session;
import um.edu.uy.business.PassengerFlightMgr;
import um.edu.uy.business.PassengerMgr;
import um.edu.uy.business.VueloMgr;
import um.edu.uy.business.entities.PassengerFlight;
import um.edu.uy.business.entities.Vuelo;

import java.sql.Time;
import java.util.List;

@Component
public class PassengerMenuController {
    @Autowired
    PassengerMgr passengerMgr;
    @Autowired
    PassengerFlightMgr passengerFlightMgr;
    @Autowired
    VueloMgr vueloMgr;
    @FXML
    private TableView<Vuelo> flightTbl;
    @FXML
    private TableView<PassengerFlight> passengerFlightTbl;
    @FXML
    private TableColumn<PassengerFlight,Integer> flightIdPF;
    @FXML
    private TableColumn<PassengerFlight,String> maletas;
    @FXML
    private TableColumn<PassengerFlight,String> checkIn;
    @FXML
    private TableColumn<Vuelo, String> airlineCol;
    @FXML
    private TableColumn<Vuelo, String> originCol;
    @FXML
    private TableColumn<Vuelo, String> destinationCol;
    @FXML
    private TableColumn<Vuelo, Time> departureCol;
    @FXML
    private TableColumn<Vuelo, Time> arrivalCol;

    @FXML
    private TableColumn<Vuelo,String> flightIDColFlight;


    public void initialize() {
        flightTbl.getItems().clear();
        airlineCol.setCellValueFactory(new PropertyValueFactory<>("ICAOaerolinea"));
        originCol.setCellValueFactory(new PropertyValueFactory<>("aeropuertoOrigen"));
        destinationCol.setCellValueFactory(new PropertyValueFactory<>("aeropuertoDestino"));
        departureCol.setCellValueFactory(new PropertyValueFactory<>("horarioSalidaEst"));
        arrivalCol.setCellValueFactory(new PropertyValueFactory<>("horarioLLegadaEst"));
        flightIDColFlight.setCellValueFactory(new PropertyValueFactory<>("id"));
        flightTbl.setPlaceholder(new Label("No rows to display"));
        String mail= Session.getInstance().getUser();
        List<PassengerFlight> passengerFlights = passengerFlightMgr.obtenerMapeoPasajero(mail);
        List<Vuelo> vuelos = passengerFlightMgr.obtenerVuelosPasajero(passengerFlights);
        for (Vuelo vuelo:vuelos) {
            flightTbl.getItems().add(vuelo);

        }
        passengerFlightTbl.getItems().clear();
        maletas.setCellValueFactory(new PropertyValueFactory<>("maletas"));
        checkIn.setCellValueFactory(new PropertyValueFactory<>("checkIn"));
        flightIdPF.setCellValueFactory(cellData -> {
            PassengerFlight passengerFlight = cellData.getValue();
            Vuelo vuelo = passengerFlight.getVuelo();
            Integer vueloId = Math.toIntExact((vuelo != null) ? vuelo.getId() : null);
            return new SimpleIntegerProperty(vueloId).asObject();
        });

        passengerFlightTbl.setPlaceholder(new Label("No rows to display"));
        for (PassengerFlight passengerFlight:passengerFlights) {
            passengerFlightTbl.getItems().add(passengerFlight);
        }
    }
    @FXML
    void close(ActionEvent actionEvent) throws Exception {
        Node source = (Node)  actionEvent.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }

}


