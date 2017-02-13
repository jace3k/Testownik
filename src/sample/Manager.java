package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Jacek on 13.02.2017.
 */
public class Manager {
    public final static String START_WINDOW = "testownikStart.fxml";
    public final static String QUEST_WINDOW = "testownikQuest.fxml";
    public final static String END_WINDOW = "testownikEnd.fxml";

    private int questShowCount;
    private int addAfterWrongCount;
    private String totalTime;
    private Stage stage;

    private ArrayList<Quest> base;
    private int baseCounter;

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

    }

    public void setQuestShowCount(int questShowCount) {
        this.questShowCount = questShowCount;
    }

    public void setAddAfterWrongCount(int addAfterWrongCount) {
        this.addAfterWrongCount = addAfterWrongCount;
    }

    public int getQuestShowCount() {
        return questShowCount;
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
}
