package um.edu.uy.ui.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import um.edu.uy.business.AvionMgr;
import um.edu.uy.business.entities.Avion;


@Component
public class AvionController {
    @Autowired
    private AvionMgr avionMgr;

    @FXML
    private Button btnClose;

    @FXML
    private TextField txmodelo;

    @FXML
    private TextField txpeso;

    @FXML
    private TextField txpas;

    @FXML
    private TextField txicao;

    @FXML
    private TextField txaerolinea;

    @FXML
    private Button btnAdd;
    @FXML
    void close(ActionEvent actionEvent) {
        Node source = (Node)  actionEvent.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    void addAvion(ActionEvent event) {
        if (txmodelo.getText() == null || txmodelo.getText().equals("") || txpeso.getText() == null || txpeso.getText().equals("") || txpas.getText() == null || txpas.getText().equals("") || txicao.getText() == null || txicao.getText().equals("") || txaerolinea.getText() == null || txaerolinea.getText().equals(""))
        {
            showAlert(
                    "Datos faltantes!",
                    "No se ingresaron los datos necesarios para completar el ingreso.")
            ;}
        else{

            String modelo = txmodelo.getText();
            String pesotx = txpeso.getText();
            String pastx = txpas.getText();
            String icao = txicao.getText();
            String aerolineatx = txaerolinea.getText();

            int pas= Integer.parseInt(pastx);
            int peso= Integer.parseInt(pesotx);
            int aerolinea = Integer.parseInt(aerolineatx);


            Avion avion = new Avion(pas, peso, modelo, icao, aerolinea);

            avionMgr.addAvion(avion);

            showAlert("Aerolinea agregada", "Se agrego con exito a la aerolinea!");

            close(event);
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
