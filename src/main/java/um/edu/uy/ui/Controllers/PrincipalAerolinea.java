package um.edu.uy.ui.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;
import um.edu.uy.Main;
import um.edu.uy.Session;

@Component
public class PrincipalAerolinea {
    @FXML
    private Button bttnAddAirplane;

    @FXML
    private Button bttnAddFlight;

    @FXML
    private Button bttnAddEmployee;

    @FXML
    private Button bttnclose;

    @FXML
    private Button bttnAddPassenger;

    @FXML
    private Button bttnChangePassword;

    @FXML
    private Button bttnCheckInPassengers;


    public void addAirlineAdmin(ActionEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        Parent root = fxmlLoader.load(AddAirlineAdminController.class.getResourceAsStream("AddAirlineEmployee.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void addAirplane(ActionEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        Parent root = fxmlLoader.load(AvionController.class.getResourceAsStream("AddAvion.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
    @FXML
    public void addFlight(ActionEvent event) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        Parent root = fxmlLoader.load(AddFlightController.class.getResourceAsStream("AddFlight.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();}
    @FXML
    public void addPassenger(ActionEvent event) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        Parent root = fxmlLoader.load(AddFlightController.class.getResourceAsStream("AddPassenger.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();}

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
    void close(ActionEvent actionEvent) throws Exception {
        logOut(actionEvent);
        Node source = (Node)  actionEvent.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }
    @FXML
    void logOut(ActionEvent actionEvent) throws Exception {
        Session.getInstance().reset();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        Parent root = fxmlLoader.load(LogInController.class.getResourceAsStream("LogInMenu.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

}
