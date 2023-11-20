package um.edu.uy.ui.Controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import um.edu.uy.Session;
import um.edu.uy.business.AeroportEmployeeMgr;
import um.edu.uy.business.AirlineMgr;
import um.edu.uy.business.AirportMgr;
import um.edu.uy.business.exceptions.AirportEmployeeAlreadyExists;
import um.edu.uy.business.exceptions.InvalidAirportEmployeeInformation;

import java.sql.Date;

@Component
public class AddAirlineEmployeeController {
    @Autowired
    private AeroportEmployeeMgr aeroportEmployeeMgr;

    @Autowired
    private AirportMgr airportMgr;

    @Autowired
    private AirlineMgr airlineMgr;


    @FXML
    private ChoiceBox <String> choiceBoxRole;

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
    private ChoiceBox<String> choiceBoxAirline;
    @FXML
    private Label airlinelbl;
    @FXML
    private Label rolelbl;


    @FXML
    void initialize()
    {
        choiceBoxRole.getItems().addAll("Administrador Aerolinea", "Encargado Check In");
        if (Session.getInstance().getAirline()!=-1) {
            choiceBoxAirline.setVisible(false);
            choiceBoxAirline.setManaged(false);
            airlinelbl.setManaged(false);
        }
        else {
            choiceBoxRole.setVisible(false);
            choiceBoxRole.setManaged(false);
            rolelbl.setManaged(false);
            choiceBoxAirline.getItems().addAll(airlineMgr.obtenerAerolineas());
        }
    }
    @FXML
    void close(ActionEvent actionEvent)
    {
        Node source = (Node)  actionEvent.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    void addAirlineEmployee(ActionEvent event) {
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
                String role = choiceBoxRole.getValue();
                long airlineid;
                if (Session.getInstance().getAirline()!=-1){
                    airlineid = Session.getInstance().getAirline();
                }
                else{
                    airlineid = airlineMgr.obtenerAerolineaPorNombre(choiceBoxAirline.getValue()).getId();
                }

                try {

                    aeroportEmployeeMgr.addAirlineEmployee(name, lastname, address, passport, nationality, birthDate, role, airlineid);

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

