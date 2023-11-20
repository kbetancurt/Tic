package um.edu.uy.ui.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import um.edu.uy.Main;

public class AirlineEmployeeMenuController {

    @FXML
    private Button bttnChangePassword;

    @FXML
    private Button bttncheckInPassengers;

    @FXML
    private Button bttnCancel;

    @FXML

    public void checkInPassengers(ActionEvent event) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        Parent root = fxmlLoader.load(AddFlightController.class.getResourceAsStream("CheckInPassengers.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();}

    @FXML
    public void changePassword(ActionEvent event) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        Parent root = fxmlLoader.load(PasswordChangeController.class.getResourceAsStream("PasswordChange.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }


    @FXML
    void close(ActionEvent actionEvent) {
        Node source = (Node)  actionEvent.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
