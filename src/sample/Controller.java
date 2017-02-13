package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable, Serializable {
    @FXML
    private Button starter;
    @FXML
    private Button load;
    @FXML
    private TextField questCounter;
    @FXML
    private TextField questAgain;
    @FXML
    private Label title;

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

        load.setOnAction(event -> {
            try {
                Manager.deserialize();
                System.out.println("Wczytano plik.");
                Manager.getInstance().setStage((Stage)((Node)event.getSource()).getScene().getWindow());
                Manager.getInstance().setActiveWindow(Manager.QUEST_WINDOW);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                System.out.println("Nie wczytano.");
                title.setText("Brak zapisanego stanu.");
            }
        });
    }
}
