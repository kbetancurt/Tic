package um.edu.uy.ui.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import um.edu.uy.business.AeroportEmployeeMgr;
import um.edu.uy.business.AirportMgr;
import um.edu.uy.business.UserInfo;
import um.edu.uy.business.VueloMgr;
import um.edu.uy.business.entities.Vuelo;
import um.edu.uy.business.exceptions.InvalidFlightInformation;
import um.edu.uy.persistence.VueloRepository;
import java.awt.*;
import java.awt.Button;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import javafx.scene.control.TextField;

@Component
public class AddFlightController {
    @Autowired
    private AeroportEmployeeMgr aeroportEmployeeMgr;

    @Autowired
    private AirportMgr airportMgr;


    @FXML
    void initialize()
    {
        choiceBoxOAirport.getItems().addAll(airportMgr.airportNameList());
        choiceBoxDAirport.getItems().addAll(airportMgr.airportNameList());
        horaSalida.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, 0));
        minutoSalida.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 0));
        horaLLegada.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, 0));
        minutoLLegada.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 0));
    }


    @FXML
    private ChoiceBox<String> choiceBoxOAirport;
    @FXML
    private DatePicker fechaSalidaPicker;

    @FXML
    private DatePicker fechaLLegadaPicker;

    @FXML
    private Spinner<Integer> horaSalida;

    @FXML
    private Spinner<Integer> minutoSalida;

    @FXML
    private Spinner<Integer> horaLLegada;

    @FXML
    private Spinner<Integer> minutoLLegada;
    @FXML
    private ChoiceBox<String> choiceBoxDAirport;
    @Autowired
    private VueloMgr vueloMgr;
    @FXML
    private Button btnClose;
    @FXML
    private TextField txtnumero;
    @FXML
    private TextField txtIATAAerolinea;
    @FXML
    private TextField txtICAO;
    @FXML
    private TextField txtaeropuertoOrigen;
    @FXML
    private TextField txtaeropuertoDestino;
    @FXML
    private TextField txtmatricula;
    @FXML
    private TextField txtasientos;
    @FXML
    private TextField txtbultos;
    @FXML
    private TextField txthorarioSalidaEst;
    @FXML
    private TextField txthorarioLLegadaEst;
    @FXML
    private TextField txthorariosSalidaRea;
    @FXML
    private TextField txthorarioLLegadaReal;
    @FXML
    private TextField txtaprobadoSalida;
    @FXML
    private TextField txtaprobadoLLegada;
    @FXML
    private TextField txtestado;

    @FXML
    private javafx.scene.control.Button btnAdd;
    @FXML
    void close(ActionEvent actionEvent) {
        Node source = (Node)  actionEvent.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }
    @FXML
    void agregarVuelo(ActionEvent event) throws IOException, InvalidFlightInformation {

        long numero= Long.parseLong(txtnumero.getText());
        String ICAO=txtICAO.getText();
        String IATAAerolinea=txtIATAAerolinea.getText();
        String aeropuertoOrigen=choiceBoxOAirport.getValue();
        String aeropuertoDestino=choiceBoxDAirport.getValue();
        String matricula=txtmatricula.getText();
        Integer asientos= Integer.valueOf(txtasientos.getText());
        Integer bultos= Integer.valueOf(txtbultos.getText());
        LocalDate salidaDate = fechaSalidaPicker.getValue();
        LocalDate llegadaDate = fechaLLegadaPicker.getValue();
        int salidaHour = horaSalida.getValue();
        int salidaMinute = minutoSalida.getValue();
        int llegadaHour = horaLLegada.getValue();
        int llegadaMinute = minutoLLegada.getValue();
        LocalDateTime salidaDateTime = LocalDateTime.of(salidaDate, LocalTime.of(salidaHour, salidaMinute));
        LocalDateTime llegadaDateTime = LocalDateTime.of(llegadaDate, LocalTime.of(llegadaHour, llegadaMinute));
        Vuelo vuelo= new Vuelo(numero,numero,IATAAerolinea,ICAO,aeropuertoOrigen,aeropuertoDestino,matricula,asientos,bultos,salidaDateTime,llegadaDateTime);
        vueloMgr.addVuelo(vuelo);
        showAlert("Vuelo agregado","Se agrego con exito el vuelo");


    }

    private void showAlert(String title, String contextText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }


}
