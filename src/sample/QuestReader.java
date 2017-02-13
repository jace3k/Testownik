package sample;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Jacek on 13.02.2017.
 */
public class QuestReader {

    private int baseCounter;
    private int questCounter;
    public QuestReader(int count) {
        questCounter = count;
    }


    public ArrayList<Quest> readBase(String folderName) {
        ArrayList<Quest> base = new ArrayList<>();;
        File baza = new File(folderName);
        if(baza.isDirectory()) {
            for(File f : baza.listFiles()) {
                base.add(new Quest(f, questCounter));
                baseCounter++;
            }
            System.out.println("Wczytano "+baseCounter+" pytań.");
        } else {
            System.out.println("to nie directory.");
            baseCounter = 0;
        }
        return base;
    }

    public int getBaseCounter() {
        return baseCounter;
    }
}
