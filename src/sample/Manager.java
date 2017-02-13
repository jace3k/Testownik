package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Jacek on 13.02.2017.
 */
public class Manager implements Serializable {
    public final static String START_WINDOW = "testownikStart.fxml";
    public final static String QUEST_WINDOW = "testownikQuest.fxml";
    public final static String END_WINDOW = "testownikEnd.fxml";

    private int questShowCount;
    private int addAfterWrongCount;
    private String totalTime;
    private transient Stage stage;

    private ArrayList<Quest> base;
    private int baseCounter;
    private int learned;

    private static Manager ourInstance = new Manager();
    private Manager() {

    }

    public static Manager getInstance() {
        return ourInstance;
    }

    public void setActiveWindow(final String window) {
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource(window))));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*
        Node node=(Node) .getSource();
        Stage stage=(Stage) node.getScene().getWindow();
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("testownikQuest.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        */
    }

    public void setQuestShowCount(int questShowCount) {
        this.questShowCount = questShowCount;
    }

    public void setAddAfterWrongCount(int addAfterWrongCount) {
        this.addAfterWrongCount = addAfterWrongCount;
    }

    public int getAddAfterWrongCount() {
        return addAfterWrongCount;
    }

    public void setTotalTime(String text) {
        totalTime = text;
    }

    public String getTotalTime() {
        return totalTime;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public ArrayList<Quest> getBase() {
        return base;
    }

    public int getBaseCounter() {
        return baseCounter;
    }

    public void read() {
        QuestReader qr = new QuestReader(questShowCount);
        base = qr.readBase("baza");
        baseCounter = qr.getBaseCounter();
    }

    public void serialize() throws IOException {
        SerializationUi.serialize(this,"sav.sav");
    }
    public static void deserialize() throws IOException, ClassNotFoundException {
        ourInstance = (Manager) SerializationUi.deserialize("sav.sav");
    }

    public int getLearned() {
        return learned;
    }
    public void incLearned() {
        learned++;
    }
}
