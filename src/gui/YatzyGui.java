package gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import models.RaffleCup;
import models.YatzyResultCalculator;

import java.net.URL;

public class YatzyGui extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Yatzy");
        GridPane pane = new GridPane();
        this.initContent(pane);
        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.show();

    }


    private int throwsLeft = 3;
    private RaffleCup rafflecup1 = new RaffleCup();
    private TextField[] textfieldsPossibleScores = new TextField[15];
    private RadioButton[] radioButtons = new RadioButton[15];
    private Label[] labelScores = new Label[15];
    private CheckBox[] checkBoxes = new CheckBox[5];
    private Label labelThrowsLeftInt = new Label(Integer.toString(throwsLeft));
    private TextField textFieldSum = new TextField("0");
    private TextField textFieldBonus = new TextField("0");
    private TextField textFieldTotal = new TextField("0");
    private ImageView[] imageViews = new ImageView[5];
    private Button buttonThrowDice = new Button("Kast terningerne");
    private Button buttonChooseCombination = new Button("Vælg kombination");

    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(5);

        for (int i = 0; i < 5; i++) {
            checkBoxes[i] = new CheckBox("Hold");
            pane.add(checkBoxes[i], i, 1);
            checkBoxes[i].setDisable(true);
            imageViews[i] = new ImageView();
            imageViews[i].setFitHeight(40);
            imageViews[i].setPreserveRatio(true);
            imageViews[i].setImage(diceImage(1));
            pane.add(imageViews[i], i, 0);
        }
        Label labelThrowsLeft = new Label("Antal kast tilbage:");
        pane.add(labelThrowsLeft, 0, 2, 2, 1);
        pane.add(labelThrowsLeftInt, 2, 2);
        pane.add(buttonThrowDice, 3, 2, 2, 1);
        pane.add(buttonChooseCombination, 3, 3, 2, 1);
        buttonChooseCombination.setDisable(true);

        // collection of the 15 radio buttons we add with labels
        addRadioButtons();
        ToggleGroup toggleGroup = new ToggleGroup();
        for (int i = 0; i < 15; i++) {
            radioButtons[i].setToggleGroup(toggleGroup);
            textfieldsPossibleScores[i] = new TextField();
            textfieldsPossibleScores[i].setMaxWidth(45);
            labelScores[i] = new Label("0");
            if (i < 6) {
                pane.add(radioButtons[i], 0, 5 + i, 2, 1);
                pane.add(textfieldsPossibleScores[i], 2, 5 + i);
                pane.add(labelScores[i], 3, 5 + i);
            } else {
                pane.add(radioButtons[i], 0, 5 + i + 2, 2, 1);
                pane.add(textfieldsPossibleScores[i], 2, 5 + i + 2);
                pane.add(labelScores[i], 3, 5 + i + 2);
            }
        }

        Label labelSum = new Label("Sum");
        Label labelBonus = new Label("Bonus");
        Label labelTotal = new Label("Total");
        pane.add(labelSum, 4, 11);
        pane.add(labelBonus, 4, 12);
        pane.add(labelTotal, 4, 22);
        pane.add(textFieldSum, 5, 11);
        pane.add(textFieldBonus, 5, 12);
        pane.add(textFieldTotal, 5, 22);
        textFieldSum.setMaxWidth(45);
        textFieldBonus.setMaxWidth(45);
        textFieldTotal.setMaxWidth(45);

        buttonThrowDice.setOnAction(event -> {
            actionThrowDice();
        });
        buttonChooseCombination.setOnAction(event1 -> {
            actionChooseCombination();
        });


    }

    public void actionThrowDice() {
        throwsLeft -= 1;
        labelThrowsLeftInt.setText(Integer.toString(throwsLeft));
        boolean[] booleans = new boolean[5];
        for (int i = 0; i < 5; i++) {
            checkBoxes[i].setDisable(false);
            booleans[i] = checkBoxes[i].isSelected();
        }
        rafflecup1.throwSomeDice(booleans);
        int[] intDice = rafflecup1.getDiceToInt();
        for (int i = 0; i < 5; i++) {
            imageViews[i].setImage(diceImage(intDice[i]));
        }

        YatzyResultCalculator yatzyResultCalculator = new YatzyResultCalculator(rafflecup1.getDice());
        for (int i = 0; i < textfieldsPossibleScores.length; i++) {
            textfieldsPossibleScores[i].setText(Integer.toString(yatzyResultCalculator.getScores()[i]));
        }

        if (throwsLeft <= 0) {
            buttonThrowDice.setDisable(true);
        }
        buttonChooseCombination.setDisable(false);
    }

    public void actionChooseCombination() {

        int sum = 0;
        for (int i = 0; i < 5; i++) {
            checkBoxes[i].setDisable(true);
            checkBoxes[i].setSelected(false);
        }
        for (int i = 0; i < radioButtons.length; i++) {
            if (radioButtons[i].isSelected()) {
                buttonChooseCombination.setDisable(true);
                buttonThrowDice.setDisable(false);
                throwsLeft = 3;
                labelThrowsLeftInt.setText(Integer.toString(throwsLeft));
                radioButtons[i].setSelected(false);
                radioButtons[i].setDisable(true);
                labelScores[i].setText(textfieldsPossibleScores[i].getText());
                if (i < 6) {
                    String sSum = Integer.toString(Integer.parseInt(textFieldSum.getText()) + Integer.parseInt(textfieldsPossibleScores[i].getText()));
                    textFieldSum.setText(sSum);
                    if (Integer.parseInt(sSum) >= 63) {
                        textFieldBonus.setText("50");
                        sum += 50;
                    }
                }

            }
            sum+=Integer.parseInt(labelScores[i].getText());

        }
        textFieldTotal.setText(Integer.toString(sum));

    }

    //this method returns an ImageView with an image of the die rolled
    public Image diceImage(int dieEyes) {
        URL urlD1 = YatzyGui.class.getResource("images/Dice-1.png");
        URL urlD2 = YatzyGui.class.getResource("images/Dice-2.png");
        URL urlD3 = YatzyGui.class.getResource("images/Dice-3.png");
        URL urlD4 = YatzyGui.class.getResource("images/Dice-4.png");
        URL urlD5 = YatzyGui.class.getResource("images/Dice-5.png");
        URL urlD6 = YatzyGui.class.getResource("images/Dice-6.png");
        Image[] images = new Image[6];
        images[0] = new Image(urlD1.toString());
        images[1] = new Image(urlD2.toString());
        images[2] = new Image(urlD3.toString());
        images[3] = new Image(urlD4.toString());
        images[4] = new Image(urlD5.toString());
        images[5] = new Image(urlD6.toString());

        return images[dieEyes - 1];
    }

    public void addRadioButtons() {
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

    public void newIntDice(int[] pInts, int[] nints) {
        for (int i = 0; i < pInts.length; i++) {
            pInts[i] = nints[i];
        }
    }
}
