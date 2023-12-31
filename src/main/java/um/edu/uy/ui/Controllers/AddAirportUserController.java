package um.edu.uy.ui.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import um.edu.uy.business.AeroportEmployeeMgr;
import um.edu.uy.business.AirportMgr;
import um.edu.uy.business.entities.AeroportEmployee;
import um.edu.uy.business.entities.Airport;
import um.edu.uy.business.exceptions.AirportEmployeeAlreadyExists;
import um.edu.uy.business.exceptions.InvalidAirportEmployeeInformation;
import org.springframework.stereotype.Component;
import um.edu.uy.persistence.AirportRepository;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

@Component
public class AddAirportUserController {

    @Autowired
    private AeroportEmployeeMgr aeroportEmployeeMgr;

    @Autowired
    private AirportMgr airportMgr;

    @FXML
    private Button btnClose;

    @FXML
    private Button btnAdd;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtAddress;
    @FXML
    private TextField txtLastname;
    @FXML
    private TextField txtPassport;
    @FXML
    private TextField txtNationality;
    @FXML
    private DatePicker datePickerBirthDate;
    @FXML
    private ChoiceBox<String> roleChoiceBox;
    @FXML
    private ChoiceBox<String> airportChoiceBox;
    @Autowired
    private AirportRepository airportRepository;

    @FXML
    void initialize()
    {
        roleChoiceBox.getItems().addAll("Administrador Aeropuerto", "Maletero");
        airportChoiceBox.getItems().addAll(airportMgr.airportNameList());
    }


    @FXML
    void close(ActionEvent actionEvent)
    {
        Node source = (Node)  actionEvent.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    void addClient(ActionEvent event) {
        if (txtName.getText() == null || txtName.getText().isEmpty() ||
                txtLastname.getText() == null || txtLastname.getText().isEmpty() ||
                txtNationality.getText() == null || txtNationality.getText().isEmpty()
                || txtPassport.getText() == null || txtPassport.getText().isEmpty() || txtAddress.getText() == null || txtAddress.getText().isEmpty()

        ) {

            showAlert(
                    "Datos faltantes!",
                    "No se ingresaron los datos necesarios para completar el ingreso.");

        } else {

            try {
                String name = txtName.getText();
                String lastname=txtLastname.getText();
                String address = txtAddress.getText();
                String passport= txtPassport.getText();
                String nationality=txtNationality.getText();
                Date birthDate = Date.valueOf(datePickerBirthDate.getValue());
                String role = roleChoiceBox.getValue();
                String airportName= airportChoiceBox.getValue();
                Airport airport= airportRepository.findOneByName(airportName);
                String airportICAO=airport.getICAO();
                String mail= aeroportEmployeeMgr.GenerateMail(name,lastname,airportICAO);

                try {

                    AeroportEmployee aeroportEmployee = new AeroportEmployee(passport,nationality,birthDate,name,lastname,address,role,airport, mail);

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
    private void showAlert(String title, String contextText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }

}
