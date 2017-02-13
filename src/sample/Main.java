package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Manager.getInstance().setStage(primaryStage);
        Parent root = FXMLLoader.load(getClass().getResource("testownikStart.fxml"));
        primaryStage.setTitle("Jackowy Testownik");
        primaryStage.getIcons().add(new Image(getClass().getResource("icon.jpg").toString()));
        primaryStage.setHeight(Screen.getPrimary().getVisualBounds().getHeight());
        primaryStage.setWidth(Screen.getPrimary().getVisualBounds().getWidth());

        //primaryStage.setMinHeight(640);
        //primaryStage.setMinWidth(1000);

        //primaryStage.setHeight(480);
        //primaryStage.setWidth(1100);

        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
