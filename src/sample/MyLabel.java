package sample;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.text.TextAlignment;

/**
 * Created by Jacek on 03.02.2017.
 */
public class MyLabel extends Label {
    private static int isColored = 0;
    public boolean isSelected = false;
    private String backupStyle;

    public MyLabel(String a) {
        super(a);
        this.setPrefHeight(80);
        this.setPrefWidth(900);
        this.setAlignment(Pos.CENTER);
        this.setTextAlignment(TextAlignment.CENTER);
        this.setWrapText(true);

        if(isColored == 1) {
            this.setStyle("-fx-background-color: #e5e5e5");
            isColored = -1;
            backupStyle = "-fx-background-color: #e5e5e5";
        }else {
            this.setStyle("-fx-background-color: #efefef");
            backupStyle = "-fx-background-color: #efefef";
        }
        isColored++;
        this.setOnMousePressed(event -> {
            if (!isSelected) {
                setSelected();
                isSelected = true;
            } else {
                setUnselected();
                isSelected = false;
            }
        });
    }

    public void setSelected() {
        this.setStyle("-fx-background-color: #ffe084");
        isSelected = true;
        System.out.println("Ustawiono styl zaznaczenia.");
    }

    public void setUnselected() {
        this.setStyle(backupStyle);
        isSelected = false;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setCorrect() {
        this.setStyle("-fx-background-color: #78ff4c");
    }
}
