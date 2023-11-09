package um.edu.uy.ui.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import um.edu.uy.Session;
import um.edu.uy.business.*;
import um.edu.uy.business.entities.Vuelo;
import um.edu.uy.business.exceptions.InvalidFlightInformation;
import um.edu.uy.persistence.AirlineRepository;

import java.awt.Button;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import javafx.scene.control.TextField;
import um.edu.uy.persistence.AirportRepository;

@Component
public class AddFlightController {
    @Autowired
    private AeroportEmployeeMgr aeroportEmployeeMgr;
    @Autowired
    private AirportRepository airportRepository;

    @Autowired
    private AirportMgr airportMgr;
    @Autowired
    private AvionMgr avionMgr;


    @FXML
    void initialize()
    {

        choiceBoxDAirport.getItems().addAll(airportMgr.airportICAOList());
        choiceBoxPlanes.getItems().addAll(avionMgr.planeList());
        horaSalida.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, 0));
        minutoSalida.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 0));
        horaLLegada.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, 0));
        minutoLLegada.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 0));
    }


    @FXML
    private ChoiceBox<String> choiceBoxPlanes;
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
    private TextField txtasientos;
    @FXML
    private TextField txtbultos;


    @FXML
    private javafx.scene.control.Button btnAdd;
    @Autowired
    private AirlineRepository airlineRepository;

    @FXML
    void close(ActionEvent actionEvent) {
        Node source = (Node)  actionEvent.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }
    @FXML
    void agregarVuelo(ActionEvent event) throws IOException, InvalidFlightInformation{

        long numero= Long.parseLong(txtnumero.getText());
        if(airlineRepository ==null){
            System.out.println("ERRROR");
        }
        String ICAO= airportRepository.findOneByICAO(Session.getInstance().getAirport()).getICAO();
        String IATAAerolinea= airlineRepository.findOneById(Session.getInstance().getAirline()).getIATA();
        String aeropuertoOrigen=Session.getInstance().getAirport();
        String aeropuertoDestino=choiceBoxDAirport.getValue();
        int end= choiceBoxPlanes.getValue().indexOf(" ");
        String matricula=choiceBoxPlanes.getValue().substring(0,end);
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
        Vuelo vuelo= new Vuelo(numero,ICAO,IATAAerolinea,aeropuertoOrigen,aeropuertoDestino,matricula,asientos,bultos,salidaDateTime,llegadaDateTime);
        if (vuelo.getAeropuertoOrigen() == null || vuelo.getAeropuertoDestino() == null || vuelo.getMatricula() == null || vuelo.getHorarioSalidaEst() == null || vuelo.getHorarioLLegadaEst() == null) {

            showAlert("Datos faltantes","No se ingresaron los datos necesarios para completar el ingreso.");


        }
        if (vuelo.getHorarioSalidaEst().isAfter(vuelo.getHorarioLLegadaEst())) {

            showAlert("Datos erroneos","La hora de llegada es antes que la de salida");

        }
        if (vuelo.getHorarioSalidaEst().isBefore(java.time.LocalDateTime.now())) {

            showAlert("Datos erroneos","La hora de salida es antes que la actual");

        }
        if (vuelo.getHorarioLLegadaEst().isBefore(java.time.LocalDateTime.now())) {

            showAlert("Datos erroneos","La hora de llegada es antes que la actual");

        }

        else {
            vueloMgr.addVuelo(vuelo);

        }

        showAlert("Vuelo agregado","Se agrego con exito el vuelo");
        close(new ActionEvent());


    }

    private void showAlert(String title, String contextText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }


}
