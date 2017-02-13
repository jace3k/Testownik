package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

public class QuestController implements Initializable, Serializable {

    private ArrayList<Quest> base = Manager.getInstance().getBase();
    private int actualQuest;

    // Layouty ///////////////
    @FXML
    private VBox labels;

    // Labele ////////////////
    @FXML
    private Label question;
    @FXML
    private Label time;
    @FXML
    private Label baseCounterLabel;
    @FXML
    private ProgressBar progress;
    @FXML
    private Label showCountLabel;
    @FXML
    private Label learnedLabel;

    // Buttony /////////////////
    @FXML
    private Button exit;
    @FXML
    private Button saveExit;
    @FXML
    private Button check;
    @FXML
    private Button next;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // BUTTONY ///////////////////////////////////////////////////
        next.setDisable(true);
        check.setDefaultButton(true);

        exit.setOnAction(event -> System.exit(0));
        saveExit.setOnAction(event -> {
            saveProgress();
            System.exit(0);
        });
        check.setOnAction(event -> {
            System.out.println("Nacisnąłeś sprawdz");
            if(!base.isEmpty()) checkIt();
            baseCounterLabel.setText(Manager.getInstance().getBaseCounter()+"");
            if(!base.isEmpty()) showCountLabel.setText(base.get(actualQuest).getShowCount()+"");
            double calculate = (double) (Manager.getInstance().getLearned())/(Manager.getInstance().getBaseCounter());
            progress.setProgress(calculate);
            next.setDisable(false);
            check.setDisable(true);
            next.setDefaultButton(true);
            check.setDefaultButton(false);

        });

        next.setOnAction((ActionEvent event) -> {
            System.out.println("Nacisnąłeś next");
            if(base.get(actualQuest).getShowCount() == 0) {
                base.remove(actualQuest);
                Manager.getInstance().incLearned();
                learnedLabel.setText(Manager.getInstance().getLearned()+"");
            }
            showQuestion();
            if(!base.isEmpty()) showCountLabel.setText(base.get(actualQuest).getShowCount()+"");
            next.setDisable(true);
            check.setDisable(false);
            check.setDefaultButton(true);
            next.setDefaultButton(false);

            if(base.isEmpty()) {
                System.out.println("Koniec pytań.");
                Manager.getInstance().setTotalTime(time.getText());
                Manager.getInstance().setActiveWindow(Manager.END_WINDOW);
            }



        });

        // LABELE ///////////////////////////////////////////////////
        baseCounterLabel.setText(Manager.getInstance().getBaseCounter()+"");
        if(!base.isEmpty()) showCountLabel.setText(base.get(actualQuest).getShowCount()+"");

        learnedLabel.setText(Manager.getInstance().getLearned()+"");

        double calculate = (double) (Manager.getInstance().getLearned())/(Manager.getInstance().getBaseCounter());
        progress.setProgress(calculate);

        displayTime();

        showQuestion();
    }

    private void checkIt() {
        int i = 0;
        boolean youFail = false;
        for(Answer a : base.get(actualQuest).getAnwsers()) {
            try {
                MyLabel mylabel = ((MyLabel) labels.getChildren().get(i));
                if (a.isGood()) mylabel.setCorrect();
                if (!a.isGood() && mylabel.isSelected()) youFail = true;
                if (a.isGood() && !mylabel.isSelected()) youFail = true;
                i++;
            } catch (Exception e) {
                System.out.println("No skończyła sie tablica.");
            }
        }
        if(!youFail) {
            base.get(actualQuest).decShowCount();
        } else base.get(actualQuest).incShowCount(Manager.getInstance().getAddAfterWrongCount());
    }

    private void showQuestion() {
        Random r = new Random();
        if(base.size()==1) actualQuest = 0;
        else if(base.isEmpty()) System.out.println("Baza jest pusta");
        else actualQuest = r.nextInt(Math.abs(base.size()));

        if(!base.isEmpty()) {
            question.setText(base.get(actualQuest).getQuestion());
            labels.getChildren().removeAll(labels.getChildren());
            for (Answer a : base.get(actualQuest).getAnwsers()) labels.getChildren().add(new MyLabel(a.getAnswer()));
        }
        baseCounterLabel.setText(Manager.getInstance().getBaseCounter()+"");
    }

    private void saveProgress() {
        Manager.getInstance().setTotalTime(time.getText());
        try {
            Manager.getInstance().serialize();
            System.out.println("Zapisano.");
        } catch (IOException e) {
            e.printStackTrace();
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
