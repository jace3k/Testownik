package sample;

import java.io.Serializable;

/**
 * Created by Jacek on 13.02.2017.
 */
public class Answer implements Serializable {
    private String answer;
    private boolean isGood;

    public Answer(String ans, boolean isG) {
        answer = ans;
        isGood = isG;
    }

    public String getAnswer() {
        return answer;
    }

    public boolean isGood() {
        return isGood;
    }
}
