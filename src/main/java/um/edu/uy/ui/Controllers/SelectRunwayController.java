package um.edu.uy.ui.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import um.edu.uy.Main;
import um.edu.uy.Session;
import um.edu.uy.business.RunwayAvailabilityMgr;
import um.edu.uy.business.entities.Gates;
import um.edu.uy.business.entities.Runway;
import um.edu.uy.business.entities.RunwayAvailability;
import um.edu.uy.business.entities.Vuelo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Component
public class SelectRunwayController {
    @Autowired
    private RunwayAvailabilityMgr runwayAvailabilityMgr;
    @FXML
    private FlowPane button_grid;


    @FXML
    void close(ActionEvent actionEvent) {
        Node source = (Node)  actionEvent.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }


    public void initialize() {
        Vuelo vuelo = Session.getInstance().getVuelo();
        String airport = Session.getInstance().getAirport();
        LocalDateTime startOccupation;
        if (Objects.equals(vuelo.getAeropuertoOrigen(), airport)) {
            startOccupation= vuelo.getHorarioSalidaEst();
        }
        else {
            startOccupation= vuelo.getHorarioLLegadaEst();
        }


        List<Runway> availableRunways = runwayAvailabilityMgr.getAvailableRunways(startOccupation);

        for (Runway runway : availableRunways) {

            Button runway_button = new Button(runway.getName());
            button_grid.getChildren().add(runway_button);
            runway_button.setOnAction(event -> {
                runwayAvailabilityMgr.occupyRunway(runway, vuelo);
                close(event);
            });

        }

    }
}
