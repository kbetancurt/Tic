package um.edu.uy.ui.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import um.edu.uy.business.VueloMgr;
import um.edu.uy.business.entities.Vuelo;
import um.edu.uy.persistence.VueloRepository;

import java.sql.Time;
import java.util.List;

@Component
public class FlightViewerController {
    @Autowired
    private VueloMgr vueloMgr;
    private VueloRepository vueloRepo;
    @FXML
    private Button ApproveBtn;
    @FXML
    private Button DeclineBtn;
    @FXML
    private TableView<Vuelo> flightTbl;
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
    private TableColumn<Vuelo, String> statusCol;

    public void initialize() {


        //set the value of each column to the corresponding attribute of the Vuelo class
        airlineCol.setCellValueFactory(new PropertyValueFactory<>("IATAAerolinea"));
        originCol.setCellValueFactory(new PropertyValueFactory<>("aeropuertoOrigen"));
        destinationCol.setCellValueFactory(new PropertyValueFactory<>("aeropuertoDestino"));
        departureCol.setCellValueFactory(new PropertyValueFactory<>("horarioSalidaEst"));
        arrivalCol.setCellValueFactory(new PropertyValueFactory<>("horarioLLegadaEst"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("estado"));
        flightTbl.setPlaceholder(new Label("No rows to display"));


        for (Vuelo vuelo : vueloMgr.obtenerVuelos()) {
            System.out.println(vuelo.getIATAAerolinea());
//            flightTbl.getItems().add(vuelo);
        }



    }














}
