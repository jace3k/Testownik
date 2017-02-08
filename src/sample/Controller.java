package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
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
        starter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Klik Start. ");
                Node node=(Node) event.getSource();
                Stage stage=(Stage) node.getScene().getWindow();
                Parent root;
                try {
                    root = FXMLLoader.load(getClass().getResource("testownikQuest.fxml"));
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                    int a = Integer.parseInt(questCounter.getText());
                    int b = Integer.parseInt(questAgain.getText());
                    QuestController.setQuestCounters(a,b);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }
}
