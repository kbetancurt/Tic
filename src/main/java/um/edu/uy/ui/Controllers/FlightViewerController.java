package um.edu.uy.ui.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import um.edu.uy.Main;
import um.edu.uy.Session;
import um.edu.uy.business.VueloMgr;
import um.edu.uy.business.entities.Vuelo;
import um.edu.uy.persistence.AirlineRepository;
import um.edu.uy.persistence.VueloRepository;

import java.sql.Time;
import java.util.Objects;

@Component
public class FlightViewerController {
    @Autowired
    private VueloMgr vueloMgr;
    @Autowired
    private VueloRepository vueloRepo;
    @Autowired
    private AirlineRepository airlineRepository;
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
    private TableColumn<Vuelo, String> approvedOriginCol;
    @FXML
    private TableColumn<Vuelo, String> approvedDestinationCol;

    public void initialize() {
        flightTbl.getItems().clear();
        airlineCol.setCellValueFactory(new PropertyValueFactory<>("IATAAerolinea"));
        originCol.setCellValueFactory(new PropertyValueFactory<>("aeropuertoOrigen"));
        destinationCol.setCellValueFactory(new PropertyValueFactory<>("aeropuertoDestino"));
        departureCol.setCellValueFactory(new PropertyValueFactory<>("horarioSalidaEst"));
        arrivalCol.setCellValueFactory(new PropertyValueFactory<>("horarioLLegadaEst"));
        approvedOriginCol.setCellValueFactory(new PropertyValueFactory<>("aprobadoSalida"));
        approvedDestinationCol.setCellValueFactory(new PropertyValueFactory<>("aprobadoLLegada"));
        flightTbl.setPlaceholder(new Label("No rows to display"));


        for (Vuelo vuelo : vueloMgr.obtenerVuelos()) {
            String Airport = Session.getInstance().getAirport();
            if (vuelo.getAeropuertoOrigen().equals(Airport) || vuelo.getAeropuertoDestino().equals(Airport)) {
                flightTbl.getItems().add(vuelo);
            }
        }

        ApproveBtn.setOnAction(event -> {
            Vuelo vuelo = flightTbl.getSelectionModel().getSelectedItem();
            if (Objects.equals(vuelo.getAeropuertoOrigen(), Session.getInstance().getAirport())) {
                vuelo.setAprobadoSalida(true);

            }
            if(Objects.equals(vuelo.getAeropuertoDestino(), Session.getInstance().getAirport())){
                vuelo.setAprobadoLLegada(true);

            }

            if (vuelo.getAprobadoLLegada() && vuelo.getAprobadoSalida()) {
                vuelo.setEstado("Aprobado");

            }
            vueloRepo.save(vuelo);
            initialize();
            Session.getInstance().setVuelo(vuelo);

            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setControllerFactory(Main.getContext()::getBean);
                Parent root = fxmlLoader.load(SelectGateController.class.getResourceAsStream("SelectGate.fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }

        });

        DeclineBtn.setOnAction(event -> {
            Vuelo vuelo = flightTbl.getSelectionModel().getSelectedItem();
            if (Objects.equals(vuelo.getAeropuertoOrigen(), Session.getInstance().getAirport())) {
                vuelo.setAprobadoSalida(false);
            }
            if(Objects.equals(vuelo.getAeropuertoDestino(), Session.getInstance().getAirport())){
                vuelo.setAprobadoLLegada(false);
            }
            vuelo.setEstado("Cancelado");
            vueloRepo.save(vuelo);
            initialize();
        });
    }



}
