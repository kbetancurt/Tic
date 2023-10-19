package um.edu.uy.ui.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import um.edu.uy.Main;
import um.edu.uy.business.AeroportEmployeeMgr;
import um.edu.uy.business.VueloMgr;
import um.edu.uy.business.entities.Vuelo;

import java.io.IOException;

@Component
public class FlightStatusChangeController {
    @Autowired
    private VueloMgr vuelomgr;
    @FXML
    private TableView<Vuelo> tablaVuelos;
    @FXML
    private TableColumn<Vuelo,String> columna;
    @FXML private Button bttnConfirmar;
    @FXML private Button bttnclose;
    @FXML
    void close(ActionEvent actionEvent) {
        Node source = (Node)  actionEvent.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }
    @FXML
    private void actualizarVuelo(ActionEvent event) throws IOException {
        if (vuelomgr==(null)) {
            System.out.println("ERORR");
            return;
        }
        String aeropuerto="";
        vuelomgr.obtenerVuelosLLeganAeropuerto(aeropuerto);
        }



    private void showAlert (String title, String contextText){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }

}

