package um.edu.uy.ui.Controllers;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import um.edu.uy.Main;
import um.edu.uy.business.AeroportEmployeeMgr;
import um.edu.uy.business.UserInfo;
import um.edu.uy.business.entities.AeroportEmployee;


import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

@Component
public class LogInController implements Initializable {
    @Autowired
    private AeroportEmployeeMgr aeroportEmployeeMgr;

    @FXML
    private TextField txtMailUser;

    @FXML
    private TextField txtPasswordUser;

    @FXML
    private Button bttnLogIn;

    @FXML
    private void logIn(ActionEvent event) throws IOException {
        if (aeroportEmployeeMgr==(null)) {
            System.out.println("ERORR");
            return;
        }
        if (txtMailUser.getText() == null || txtMailUser.getText().equals("") ||
                txtPasswordUser.getText() == null || txtPasswordUser.getText().equals("")) {

            showAlert(
                    "Datos faltantes!",
                    "No se ingresaron los datos necesarios para completar el ingreso.");

        } else {
            if (aeroportEmployeeMgr.getAirportEmployee(txtMailUser.getText()) == null || !Objects.equals(aeroportEmployeeMgr.getAirportEmployee(txtMailUser.getText()).password, txtPasswordUser.getText())) {
                showAlert("Datos Incorrectos!", "Mail o contraseña incorrectos");

            } else {
                AeroportEmployee employee =aeroportEmployeeMgr.getAirportEmployee(txtMailUser.getText());
                UserInfo.userEmail=txtMailUser.getText();
                UserInfo.employee=employee;
                if (employee.password.equals(employee.passport)){
                    close(event);
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setControllerFactory(Main.getContext()::getBean);
                    Parent root = fxmlLoader.load(PasswordChangeController.class.getResourceAsStream("PasswordChange.fxml"));
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.show();
                }
                else{

                    if(employee.role.equals("Administrador Aeropuerto")){
                        close(event);
                        FXMLLoader fxmlLoader = new FXMLLoader();
                        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
                        Parent root = fxmlLoader.load(AirportAdminMenuController.class.getResourceAsStream("AirportAdminMenu.fxml"));
                        Stage stage = new Stage();
                        stage.setScene(new Scene(root));
                        stage.show();}
                    if(employee.role.equals("Maletero")){

                    }
                   if (employee.role.equals("Administrador Aerolinea")){
                        FXMLLoader fxmlLoader = new FXMLLoader();
                        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
                        Parent root = fxmlLoader.load(PrincipalAerolinea.class.getResourceAsStream("PrincipalAerolinea.fxml"));
                        Stage stage = new Stage();
                        stage.setScene(new Scene(root));
                        stage.show();}
                    }
            }}
        }


    private void clean() {
            txtPasswordUser.setText(null);
            txtMailUser.setText(null);
        }
    @FXML
    void close(ActionEvent actionEvent) {
        Node source = (Node)  actionEvent.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }

    private void showAlert (String title, String contextText){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(contextText);
            alert.showAndWait();
        }

    @Override
    public void initialize (URL location, ResourceBundle resources){
        }

}
