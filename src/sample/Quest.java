package sample;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by Jacek on 03.02.2017.
 */
public class Quest implements Serializable {
    private String firstLine;
    private String question;
    private ArrayList<Answer> answers = new ArrayList<>();
    private int showCount;

    public Quest(File f, int qc) {
        showCount = qc;
        String currentLine;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f), "ISO-8859-2"));

            firstLine = br.readLine();
            question = br.readLine();
            int i = 1;
            while((currentLine = br.readLine()) != null ) {
                answers.add(new Answer(currentLine,isAnswerGood(i)));
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean isAnswerGood(int i) {
        try {
            return firstLine.charAt(i) == '1';
        } catch (StringIndexOutOfBoundsException ex) {
            return false;
        }
    }

    public String getQuestion() {
        return question;
    }

    public ArrayList<Answer> getAnwsers() {
        return answers;
    }

    public int getShowCount() {
        return showCount;
    }

    public void decShowCount() {
        this.showCount--;
    }

    public void incShowCount(int more) {
        this.showCount+=more;
    }

}
