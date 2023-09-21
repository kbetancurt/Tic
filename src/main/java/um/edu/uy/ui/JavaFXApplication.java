package um.edu.uy.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;
import um.edu.uy.Main;
import um.edu.uy.business.entities.AeroportEmployee;

import java.sql.Date;
import java.util.Objects;

public class JavaFXApplication extends Application  {

    private Parent root;

    @Override
    public void init() throws Exception {

    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        AeroportEmployee aeroportEmployee = new AeroportEmployee(123434, "123123","nationality", new Date(2000,11,27), "juan", "perez", "address1","admin");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LogInMenu.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    @Override
    public void stop() {
        Main.getContext().close();
    }

}
