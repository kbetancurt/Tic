package um.edu.uy.ui.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;
import um.edu.uy.Main;

@Component
public class Principal {

    @FXML
    private MenuItem mItemAgregarCliente;
    @FXML
    private MenuItem mItemAddAirlane;


    @FXML
    public void addAirlane(ActionEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        Parent root = fxmlLoader.load(AddAirlineController.class.getResourceAsStream("AddAirlane.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

}
