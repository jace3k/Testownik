package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private Button starter;
    @FXML
    private TextField questCounter;
    @FXML
    private TextField questAgain;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        starter.setDefaultButton(true);
        starter.setOnAction(event -> {
            System.out.println("Klik Start. ");
            Manager.getInstance().setQuestShowCount(Integer.parseInt(questCounter.getText()));
            Manager.getInstance().setAddAfterWrongCount(Integer.parseInt(questAgain.getText()));
            Manager.getInstance().read();
            Manager.getInstance().setActiveWindow(Manager.QUEST_WINDOW);
        });
    }
}
