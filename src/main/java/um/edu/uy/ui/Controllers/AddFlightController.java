package um.edu.uy.ui.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import um.edu.uy.business.UserInfo;
import um.edu.uy.business.VueloMgr;
import um.edu.uy.business.entities.Vuelo;
import um.edu.uy.persistence.VueloRepository;
import java.awt.*;
import java.io.IOException;
import java.sql.Time;

@Component
public class AddFlightController {
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
    void agregarVuelo(ActionEvent event) throws IOException{
        try{
        long numero= Long.parseLong(txtnumero.getText());
        String ICAO=txtICAO.getText();
        String IATAAerolinea=txtIATAAerolinea.getText();
        String aeropuertoOrigen=txtaeropuertoOrigen.getText();
        String aeropuertoDestino=txtaeropuertoDestino.getText();
        String matricula=txtmatricula.getText();
        Integer asientos= Integer.valueOf(txtasientos.getText());
        Integer bultos= Integer.valueOf(txtbultos.getText());
        Time horarioSalidaEst= Time.valueOf(txthorarioSalidaEst.getText());
        Time horarioLLegadEst= Time.valueOf(txthorarioLLegadaEst.getText());
        Vuelo vuelo= new Vuelo(numero,numero,IATAAerolinea,ICAO,aeropuertoOrigen,aeropuertoDestino,matricula,asientos,bultos,horarioSalidaEst,horarioLLegadEst);

            vueloMgr.addVuelo(vuelo);
        }
        catch (NumberFormatException e) {
            throw new RuntimeException(e);
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
