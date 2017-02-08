package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

public class QuestController implements Initializable {
    private static int quest1;
    private static int quest2;
    private ArrayList<Quest> base = new ArrayList<>();
    private ArrayList<Integer> randomizedNumbers = new ArrayList<>();
    private int actualQuest;
    private Label actualLabel;
    private int x = 1;
    // Layouty ///////////////
    @FXML
    private VBox labels;

    // Labele ////////////////
    @FXML
    private Label question;
    @FXML
    private Label time;
    @FXML
    private Label baseCounter;
    @FXML
    private ProgressBar progress;

    // Buttony /////////////////
    @FXML
    private Button exit;
    @FXML
    private Button saveExit;
    @FXML
    private Button check;
    @FXML
    private Button next;

    public static void setQuestCounters(int a, int b) {
        quest1 = a;
        quest2 = b;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Inicjalizacja QuestController");

        // BUTTONY ///////////////////////////////////////////////////

        next.setDisable(true);
        check.setDefaultButton(true);

        exit.setOnAction(event -> System.exit(0)); // guzik wyjście
        saveExit.setOnAction(event -> { // guzik wyjście i zapisz
            saveProgress();
            System.exit(0);
        });
        check.setOnAction(event -> {
            // sprawdzanie odpowiedzi czy dobrze
            checkIt();

            // button disable i enable next
            next.setDisable(false);
            check.setDisable(true);
            next.setDefaultButton(true);
            check.setDefaultButton(false);
        });

        next.setOnAction((ActionEvent event) -> {
            // następne pytanie
            // button disable i enable check

            showQuestion();
            next.setDisable(true);
            check.setDisable(false);
            check.setDefaultButton(true);
            next.setDefaultButton(false);
            double calculate = (double) x/(Integer.parseInt(baseCounter.getText()));
            progress.setProgress(calculate);
            x++;
            System.out.println("X="+x);
            if(base.isEmpty()) {
                // nowa scena
                System.out.println("Koniec pytań.");
                Node node=(Node) event.getSource();
                Stage stage=(Stage) node.getScene().getWindow();
                Parent root;
                try {
                    EndController.temp = time.getText();
                    root = FXMLLoader.load(getClass().getResource("testownikEnd.fxml"));
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


        // LABELE ///////////////////////////////////////////////////


        // WCZYTYWANIE /////////////////////////////////////////////
        readBase();
        showQuestion();
        displayTime();
    }

    private void checkIt() {
        try {
            if(!base.isEmpty()) {
                for (int i = 0; i < base.get(actualQuest).alength(); i++) {
                    if (base.get(actualQuest).getAnswerGoodAt(i) == 1) {
                        ((Label) labels.getChildren().get(i)).setStyle("-fx-background-color: lawngreen");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Pewnie zle zakodowany plik.");
        }
    }

    private void showQuestion() {
        // odświeżanie ekranu i pokazanie nowego pytania
        Random r = new Random();


        if(base.size()==1) actualQuest = 0;
        else if(base.isEmpty()) System.out.println("Baza jest pusta");
        else actualQuest = r.nextInt(Math.abs(base.size()-1));


        if(!base.isEmpty()) {
            question.setText(base.get(actualQuest).getQuestion());
            randomizedNumbers.add(actualQuest);
            //generowanie labeli
            labels.getChildren().removeAll(labels.getChildren());
            for (int i = 0; i < base.get(actualQuest).alength(); i++) {
                //myLabels.add(new MyLabel(base.get(actualQuest).getAnswerAt(i)));
                labels.getChildren().add(new MyLabel(base.get(actualQuest).getAnswerAt(i)));
            }
            base.remove(actualQuest);
            //baseCounter.setText((base.size())+"");
        }
    }

    private void saveProgress() {
        // zapisywanie postępu
    }

    private void readBase() {
        // wczytywanie pytań
        //File baza = new File("C:\\Users\\Jacek\\IdeaProjects\\TestownikReal\\src\\baza");
        File baza = new File("" +
                "baza");
        if(baza.isDirectory()) {
            int x = 0;
            for(File f : baza.listFiles()) {
                //System.out.println(f.getName());
                base.add(new Quest(f));
                x++;
            }
            baseCounter.setText(x+"");
            System.out.println("Wczytano "+x+" pytań.");
        } else {
            System.out.println("to nie directory.");
        }
    }
    private void displayTime() {
        Timeline tl = new Timeline();
        tl.setCycleCount(Animation.INDEFINITE);
        KeyFrame move = new KeyFrame(Duration.millis(1000), new EventHandler<ActionEvent>() {
            int e = 1;
            LocalTime timeOfDay;
            @Override
            public void handle(ActionEvent event) {
                timeOfDay = LocalTime.ofSecondOfDay(e);
                time.setText(timeOfDay.toString());
                e++;
            }
        });
        tl.getKeyFrames().add(move);
        tl.play();
    }
}
