package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.Serializable;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Jacek on 04.02.2017.
 */
public class EndController implements Initializable, Serializable {
    @FXML
    private Button endButton;
    @FXML
    private Label endTime;
    @FXML
    private Button restart;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        endTime.setText(Manager.getInstance().getTotalTime());
        endButton.setDefaultButton(true);
        endButton.setOnAction(event -> System.exit(0));
        restart.setOnAction(event -> Manager.getInstance().setActiveWindow(Manager.START_WINDOW));
    }
}
