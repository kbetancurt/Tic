package um.edu.uy.ui.Controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import um.edu.uy.business.AeroportEmployeeMgr;
import um.edu.uy.business.AirlaneMgr;
import um.edu.uy.business.entities.AeroportEmployee;
import um.edu.uy.business.entities.Airlane;
import um.edu.uy.business.exceptions.AirportEmployeeAlreadyExists;
import um.edu.uy.business.exceptions.InvalidAirportEmployeeInformation;
import org.springframework.stereotype.Component;

import java.sql.Date;
@Component
public class AirlaneController {
    @Autowired
    private AirlaneMgr airlaneMgr;

    @FXML
    private Button btnClose;

    @FXML
    private TextField txtName;

    @FXML
    private Button btnAdd;
    @FXML
    void close(ActionEvent actionEvent) {
        Node source = (Node)  actionEvent.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    void addAirline(ActionEvent event) {
        if (txtName.getText() == null || txtName.getText().equals(""))
 {
            showAlert(
                    "Datos faltantes!",
                    "No se ingresaron los datos necesarios para completar el ingreso.")
        ;}
        else{


                String name = txtName.getText();
                 {

                    Airlane airlane = new Airlane(name);

                    airlaneMgr.addAirlane(airlane);

                    showAlert("Aerolinea agregada", "Se agrego con exito a la aerolinea!");

                    close(event);
                }

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


