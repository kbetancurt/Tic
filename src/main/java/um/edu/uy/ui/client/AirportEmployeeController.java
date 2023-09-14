package um.edu.uy.ui.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import um.edu.uy.business.AeroportEmployeeMgr;
import um.edu.uy.business.entities.AeroportEmployee;
import um.edu.uy.business.exceptions.AirportEmployeeAlreadyExists;
import um.edu.uy.business.exceptions.InvalidAirportEmployeeInformation;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
public class AirportEmployeeController {

    @Autowired
    private AeroportEmployeeMgr aeroportEmployeeMgr;

    @FXML
    private Button btnClose;

    @FXML
    private TextField txtName;

    @FXML
    private Button btnAdd;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtDocument;

    @FXML
    void close(ActionEvent actionEvent) {
        Node source = (Node)  actionEvent.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    void addClient(ActionEvent event) {
        if (txtDocument.getText() == null || txtDocument.getText().equals("") ||
        txtAddress.getText() == null || txtAddress.getText().equals("") ||
        txtAddress.getText() == null || txtAddress.getText().equals("")) {

            showAlert(
            "Datos faltantes!",
            "No se ingresaron los datos necesarios para completar el ingreso.");

        } else {

            try {

                Long document = Long.valueOf(txtDocument.getText());
                String name = txtName.getText();
                String address = txtAddress.getText();
                String passport= txtAddress.getText();
                String nationality=txtAddress.getText();
                Date birthDate= Date.valueOf(txtAddress.getText());
                String lastname=txtAddress.getText();
                String role= txtAddress.getText();

                try {

                    AeroportEmployee aeroportEmployee = new AeroportEmployee(document, passport,nationality, birthDate, name, lastname, address,role);

                    aeroportEmployeeMgr.addClient(aeroportEmployee);

                    showAlert("Cliente agregado", "Se agrego con exito el cliente!");

                    close(event);
                } catch (InvalidAirportEmployeeInformation invalidAirportEmployeeInformation) {
                    showAlert(
                            "Informacion invalida !",
                            "Se encontro un error en los datos ingresados.");
                } catch (AirportEmployeeAlreadyExists airportEmployeeAlreadyExists) {
                    showAlert(
                            "Documento ya registrado !",
                            "El documento indicado ya ha sido registrado en el sistema).");
                }

            } catch (NumberFormatException e) {

                showAlert(
                        "Datos incorrectos !",
                        "El documento no tiene el formato esperado (numerico).");

            }
        }

    }

    private void clean() {
        txtDocument.setText(null);
        txtAddress.setText(null);
        txtName.setText(null);
    }

    private void showAlert(String title, String contextText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }

}
