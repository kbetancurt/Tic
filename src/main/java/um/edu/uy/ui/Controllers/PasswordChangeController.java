package um.edu.uy.ui.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import um.edu.uy.Main;
import um.edu.uy.business.AeroportEmployeeMgr;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
@Component
public class PasswordChangeController {

    @Autowired
    private AeroportEmployeeMgr aeroportEmployeeMgr;

    @FXML
    private TextField txtNewPassword;
    @FXML
    private TextField txtCheckNewPassword;


    @FXML
    private TextField txtPasswordUser;
    @FXML
    private TextField txtMail;


    @FXML private Button bttnConfirmar;
    @FXML
    void close(ActionEvent actionEvent) {
        Node source = (Node)  actionEvent.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }
        @FXML
        private void passwordChange(ActionEvent event) throws IOException {
            if (aeroportEmployeeMgr==(null)) {
                System.out.println("ERORR");
                return;
            }
            if (txtNewPassword.getText() == null || txtNewPassword.getText().equals("") ||
                    txtCheckNewPassword.getText() == null || txtCheckNewPassword.getText().equals("") ||
                    txtPasswordUser.getText() == null || txtPasswordUser.getText().equals("")) {

                showAlert(
                        "Datos faltantes!",
                        "No se ingresaron los datos necesarios para cambiar la contraseña .");


            } else {
                    System.out.println(txtNewPassword.getText());
                    aeroportEmployeeMgr.updatePassword(aeroportEmployeeMgr.getAirportEmployee(txtMail.getText()),txtNewPassword.getText());
                    showAlert("Contraseña actualizada","Se actualizo con exito la contraseña");

                    close(event);
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setControllerFactory(Main.getContext()::getBean);

                    Parent root = fxmlLoader.load(Principal.class.getResourceAsStream("Principal.fxml"));
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.show();
                }
            }


        private void showAlert (String title, String contextText){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(contextText);
            alert.showAndWait();
        }

    }

