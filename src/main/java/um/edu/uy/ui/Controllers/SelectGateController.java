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
import um.edu.uy.business.GateAvailabilityMgr;
import um.edu.uy.business.entities.Gates;
import um.edu.uy.business.entities.Vuelo;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

@Component
public class SelectGateController {
    @Autowired
    private GateAvailabilityMgr gateAvailabilityMgr;

    @FXML
    private FlowPane button_grid;


    @FXML
    void close(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setControllerFactory(Main.getContext()::getBean);
            Parent root = fxmlLoader.load(SelectGateController.class.getResourceAsStream("SelectRunway.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
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


                List<Gates> availableGates = gateAvailabilityMgr.getAvailableGates(startOccupation);

                for (Gates gate : availableGates) {

                    Button gate_button = new Button(gate.getName());
                    button_grid.getChildren().add(gate_button);
                    gate_button.setOnAction(event -> {gateAvailabilityMgr.occupyGate(gate, vuelo);
                        close(event);
                    });

                }

            }
    }

