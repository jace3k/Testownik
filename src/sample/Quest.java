package sample;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by Jacek on 03.02.2017.
 */
public class Quest {
    private String firstLine;
    private String question;
    private ArrayList<String> anwsers = new ArrayList<>();
    private ArrayList<Integer> anwsersGood = new ArrayList<>();
    private boolean done = false;

    public Quest(File f) {
        String currentLine;
        try {

            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f), "ISO-8859-2"));

            //BufferedReader br = new BufferedReader(new FileReader(f));
            while((currentLine = br.readLine()) != null ) {
                //currentLine = new String(currentLine.getBytes(), "UTF-8");
                anwsers.add(currentLine);
            }
            firstLine = anwsers.get(0);
            anwsers.remove(0);
            question = anwsers.get(0);
            anwsers.remove(0);
            setAnwsersGood();
            done = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setAnwsersGood() {
        for(int i = 1; i < firstLine.length(); i++) {
            anwsersGood.add(Integer.parseInt(firstLine.charAt(i)+""));
        }
    }
    public String getQuestion() {
        return question;
    }

    public String getAnswerAt(int i) {
        return anwsers.get(i);
    }
    public int getAnswerGoodAt(int i) {
        return anwsersGood.get(i);
    }

    public int alength() {
        return anwsersGood.size();
    }

}
