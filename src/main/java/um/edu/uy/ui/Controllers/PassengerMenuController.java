package um.edu.uy.ui.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
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
    private TableColumn<Vuelo, String> approvedOriginCol;
    @FXML
    private TableColumn<Vuelo, String> approvedDestinationCol;
    @FXML
    private TableColumn<Vuelo,String> flightIDColFlight;


    public void initialize() {
        flightTbl.getItems().clear();
        airlineCol.setCellValueFactory(new PropertyValueFactory<>("IATAAerolinea"));
        originCol.setCellValueFactory(new PropertyValueFactory<>("aeropuertoOrigen"));
        destinationCol.setCellValueFactory(new PropertyValueFactory<>("aeropuertoDestino"));
        departureCol.setCellValueFactory(new PropertyValueFactory<>("horarioSalidaEst"));
        arrivalCol.setCellValueFactory(new PropertyValueFactory<>("horarioLLegadaEst"));
        approvedOriginCol.setCellValueFactory(new PropertyValueFactory<>("aprobadoSalida"));
        approvedDestinationCol.setCellValueFactory(new PropertyValueFactory<>("aprobadoLLegada"));
        flightIDColFlight.setCellValueFactory(new PropertyValueFactory<>("id"));
        flightTbl.setPlaceholder(new Label("No rows to display"));
        String mail= Session.getInstance().getUser();
        List<PassengerFlight> passengerFlights = passengerFlightMgr.obtenerMapeoPasajero(mail);
        List<Vuelo> vuelos = passengerFlightMgr.obtenerVuelosPasajero(passengerFlights);
        for (Vuelo vuelo:vuelos) {
            flightTbl.getItems().add(vuelo);
        }
        passengerFlightTbl.getItems().clear();
        flightIdPF.setCellValueFactory(new PropertyValueFactory<>("vuelo"));
        maletas.setCellValueFactory(new PropertyValueFactory<>("maletas"));
        checkIn.setCellValueFactory(new PropertyValueFactory<>("checkIn"));
        passengerFlightTbl.setPlaceholder(new Label("No rows to display"));
        for (PassengerFlight passengerFlight:passengerFlights) {
            passengerFlightTbl.getItems().add(passengerFlight);
        }
    }}


