package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("testownikStart.fxml"));
        primaryStage.setTitle("Jackowy Testownik");
        primaryStage.getIcons().add(new Image(getClass().getResource("icon.jpg").toString()));
        primaryStage.setMinHeight(800);
        primaryStage.setMinWidth(1300);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
