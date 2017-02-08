package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Jacek on 04.02.2017.
 */
public class EndController implements Initializable {
    @FXML
    private Button endButton;
    @FXML
    private Label endTime;

    public static String temp;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        endTime.setText(temp);
        endButton.setDefaultButton(true);
        endButton.setOnAction(event -> System.exit(0));

    }
}
