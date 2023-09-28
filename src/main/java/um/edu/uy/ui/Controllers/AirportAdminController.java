package um.edu.uy.ui.Controllers;

import javafx.event.EventHandler;
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
import javafx.scene.Scene;
import org.springframework.beans.factory.annotation.Autowired;
import um.edu.uy.Main;
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

public class AirportAdminController implements Initializable {

    @FXML
    private Button addUser;

    @FXML
    private Button addAirline;

    @FXML
    private void AirportAdmin(ActionEvent event) throws IOException {

        addUser.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("AddUser.fxml"));
                Parent root = null;
                try {
                    root = loader.load();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                Scene scene = new Scene(root);
                Stage primaryStage = new Stage();
                primaryStage.setScene(scene);
                primaryStage.show();
            }
        });

        EventHandler<ActionEvent> airlineEvent = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("AddAirline.fxml"));
                Parent root = null;
                try {
                    root = loader.load();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                Scene scene = new Scene(root);
                Stage primaryStage = new Stage();
                primaryStage.setScene(scene);
                primaryStage.show();
            }
        };
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}