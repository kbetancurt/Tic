package um.edu.uy.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import um.edu.uy.Main;
import um.edu.uy.ui.Controllers.LogInController;

public class JavaFXApplication extends Application  {

    private Parent root;

    @Override
    public void init() throws Exception {

    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        Parent root = fxmlLoader.load(LogInController.class.getResourceAsStream("LogInMenu.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();

    }

    @Override
    public void stop() {
        Main.getContext().close();
    }

}
