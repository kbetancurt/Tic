package um.edu.uy.ui.Controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import um.edu.uy.Main;
import um.edu.uy.Session;
import um.edu.uy.business.AirlaneMgr;
import um.edu.uy.business.UserInfo;
import um.edu.uy.business.entities.Airline;
import org.springframework.stereotype.Component;
import um.edu.uy.business.exceptions.AirlineAlreadyExists;
import um.edu.uy.business.exceptions.InvalidAirlineInformation;

import java.io.IOException;

@Component
public class AddAirlineController {
    @Autowired
    private AirlaneMgr airlaneMgr;

    @FXML
    private Button btnClose;

    @FXML
    private TextField txtName;
    @FXML
    private TextField txtIata;
    @FXML
    private TextField txtIcao;
    @FXML
    private TextField txtCountry;

    @FXML
    private Button btnAdd;
    @FXML
    void close(ActionEvent actionEvent) {
        Node source = (Node)  actionEvent.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    void addAirline(ActionEvent event) throws IOException{
        if (txtName.getText() == null || txtName.getText().isEmpty() ||
                txtCountry.getText() == null || txtCountry.getText().isEmpty() ||
                txtIata.getText() == null || txtIata.getText().isEmpty() ||
                txtIcao.getText() == null || txtIcao.getText().isEmpty()
        )
 {
            showAlert(
                    "Datos faltantes!",
                    "No se ingresaron los datos necesarios para completar el ingreso.")
        ;}
        else{


                String name = txtName.getText();
                String IATA= txtIata.getText();
                String ICAO=txtIcao.getText();
                String pais=txtCountry.getText();

                 {

                    Airline airline = new Airline(name,IATA,ICAO,pais);

                     try {
                         airlaneMgr.addAirlane(airline);
                         showAlert("Aerolinea agregada", "Se agrego con exito a la aerolinea!");
                         UserInfo.airlineInfo=airline;
                         Session.getInstance().setAirline(airline.getId());
                         FXMLLoader fxmlLoader = new FXMLLoader();
                         fxmlLoader.setControllerFactory(Main.getContext()::getBean);
                         Parent root = fxmlLoader.load(AddAirlineAdminController.class.getResourceAsStream("AddAirlineAdmin.fxml"));
                         Stage stage = new Stage();
                         stage.setScene(new Scene(root));
                         stage.show();
                     } catch (AirlineAlreadyExists airlineAlreadyExists) {
                         showAlert(
                                 "La aerolinea ya existe !",
                                 "La aerolinea ya ha sido registrada.");;
                     } catch (InvalidAirlineInformation invalidAirlineInformation) {
                         showAlert(
                                 "Informacion invalida !",
                                 "Se encontro un error en los datos ingresados.");
                     }


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


