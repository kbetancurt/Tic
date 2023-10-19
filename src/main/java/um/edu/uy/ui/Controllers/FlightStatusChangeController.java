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
import java.util.List;

@Component
public class FlightStatusChangeController {
    @Autowired
    private VueloMgr vuelomgr;
    @FXML
    private TableView<Vuelo> tablaVuelosLLegada;
    @FXML
    private TableColumn<Vuelo,String> IATA;
    @FXML
    private TableColumn<Vuelo,String> ICAO;
    @FXML
    private TableColumn<Vuelo,String>  horarioEstLLegada;
    @FXML
    private TableColumn<Vuelo,String> aeropuertoOrigen;
    @FXML
    private TableColumn<Vuelo,String> tamaño;
    @FXML private Button bttnConfirmar;
    @FXML private Button bttnclose;
    @FXML
    void close(ActionEvent actionEvent) {
        Node source = (Node)  actionEvent.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }
    @FXML
    private void actualizarVuelolLegada(ActionEvent event) throws IOException {
        if (vuelomgr == null) {
            System.out.println("ERROR: VueloMgr is null");
            return;
        }
        String aeropuerto = "San Pablo";
        // Assuming you want to get the selected flight from the table
        Vuelo selectedFlight = tablaVuelosLLegada.getSelectionModel().getSelectedItem();

        if (selectedFlight == null) {
            showAlert("Error", "Please select a flight from the table.");
            return;
        }

        // Now you can update the flight status or perform any other logic
        vuelomgr.actualizarVueloLLegada(selectedFlight, true);

        // Show a confirmation message
        showAlert("Success", "Flight status updated successfully.");
    }




    private void showAlert (String title, String contextText){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }

}

