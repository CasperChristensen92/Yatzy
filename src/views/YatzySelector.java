package views;

import javafx.scene.control.RadioButton;
import javafx.scene.layout.VBox;

public class YatzySelector {
    RadioButton[] radioButtons = new RadioButton[15];

    public YatzySelector(){

    }

    public void addRadioButtons(){
        radioButtons[0] = new RadioButton("1'ere");
        radioButtons[1] = new RadioButton("2'ere");
        radioButtons[2] = new RadioButton("3'ere");
        radioButtons[3] = new RadioButton("4'ere");
        radioButtons[4] = new RadioButton("5'ere");
        radioButtons[5] = new RadioButton("6'ere");
        radioButtons[6] = new RadioButton("Et par");
        radioButtons[7] = new RadioButton("To par");
        radioButtons[8] = new RadioButton("3 ens");
        radioButtons[9] = new RadioButton("4 ens");
        radioButtons[10] = new RadioButton("Lille straight");
        radioButtons[11] = new RadioButton("Store straight");
        radioButtons[12] = new RadioButton("Fuldt hus");
        radioButtons[13] = new RadioButton("Chance");
        radioButtons[14] = new RadioButton("Yatzy");
    }
    public VBox getVBoxUpper(){
        VBox vBox = new VBox();
        vBox.setSpacing(10);
        for (int i = 0; i < 6; i++) {
            vBox.getChildren().add(radioButtons[i]);
        }
        return vBox;
    }
    public VBox getVBoxLower(){
        VBox vBox = new VBox();
        vBox.setSpacing(10);
        for (int i = 6; i < 15; i++) {
            vBox.getChildren().add(radioButtons[i]);

        }
        return vBox;
    }

    public RadioButton[] getRadioButtons() {
        return radioButtons;
    }
}
