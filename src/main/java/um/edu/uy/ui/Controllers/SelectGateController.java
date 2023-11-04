package um.edu.uy.ui.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import um.edu.uy.Session;
import um.edu.uy.business.GateAvailabilityMgr;
import um.edu.uy.business.entities.Gates;
import um.edu.uy.business.entities.Vuelo;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class SelectGateController {
    @Autowired
    private GateAvailabilityMgr gateAvailabilityMgr;

    @FXML
    private FlowPane button_grid;





    public void initialize() {
        button_grid.sceneProperty().addListener((observableScene, oldScene, newScene) -> {
            if (oldScene == null && newScene != null) {
                Stage stage = (Stage) newScene.getWindow();
                Vuelo vuelo = Session.getInstance().getVuelo();
                LocalDateTime startOccupation = vuelo.getHorarioSalidaEst();
                List<Gates> availableGates = gateAvailabilityMgr.getAvailableGates(startOccupation);

                for (Gates gate : availableGates) {

                    Button gate_button = new Button(gate.getName());button_grid.getChildren().add(gate_button);
                    gate_button.setOnAction(event -> {gateAvailabilityMgr.occupyGate(gate, vuelo);stage.close();
                    });
                }

            }
        });
    }
}
