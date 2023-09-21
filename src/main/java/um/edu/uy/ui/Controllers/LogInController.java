package um.edu.uy.ui.Controllers;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;


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
import um.edu.uy.persistence.AeroportEmployeeRepository;
import um.edu.uy.ui.Principal;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

public class LogInController implements Initializable {
    @Autowired
    private AeroportEmployeeRepository aeroportEmployeeRepository;

    @FXML
    private TextField txtMailUser;

    @FXML
    private TextField txtPasswordUser;

    @FXML
    private Button bttnLogIn;

    @FXML
    private void logIn(ActionEvent event) throws IOException {
        if (txtMailUser.getText() == null || txtMailUser.getText().equals("") ||
                txtPasswordUser.getText() == null || txtPasswordUser.getText().equals("")) {

            showAlert(
                    "Datos faltantes!",
                    "No se ingresaron los datos necesarios para completar el ingreso.");

        } else {
            /*if (aeroportEmployeeRepository.findOneByMail(txtMailUser.getText()) ==null ||  !Objects.equals(aeroportEmployeeRepository.findOneByMail(txtMailUser.getText()).password, txtPasswordUser.getText()))
            {
                showAlert(
                        "Datos Incorrectos!",
                        "Mail o contraseña incorrectos");
            }
                else{

                FXMLLoader loader = new FXMLLoader(getClass().getResource("AddClient.fxml"));
                Parent root = loader.load();
                ClientController clientController = loader.getController();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();*/
            FXMLLoader loader = new FXMLLoader(getClass().getResource("LogInMenu.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();


            }
        }
    }

    private void clean() {
        txtPasswordUser.setText(null);
        txtMailUser.setText(null);
    }

    private void showAlert(String title, String contextText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
