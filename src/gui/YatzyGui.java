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
import views.YatzySelector;

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

    private YatzySelector yatzySelector;
    private int throwsLeft = 2;
    RaffleCup rc1 = new RaffleCup();
    TextField[] txtfsPossibleScores = new TextField[15];

    private void initContent(GridPane pane) {
        YatzyResultCalculator yatzyResultCalculator = new YatzyResultCalculator(rc1.getDice());
        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(5);

        int[] intDice = rc1.getDiceToInt();

        for (int i = 0; i < 5; i++) {
            pane.add(diceImage(intDice[i] - 1), i, 0);
        }

        CheckBox[] checkBoxes = new CheckBox[5];
        for (int i = 0; i < 5; i++) {
            checkBoxes[i] = new CheckBox("Hold");
            pane.add(checkBoxes[i], i, 1);
        }

        boolean[] booleans = new boolean[5];

        Label lblThrowsLeft = new Label("Antal kast tilbage:");
        Label lblThrowsLeftInt = new Label(Integer.toString(throwsLeft));

        pane.add(lblThrowsLeft, 0, 2, 2, 1);
        pane.add(lblThrowsLeftInt, 2, 2);
        Button btnThrowDice = new Button("Kast terningerne");
        pane.add(btnThrowDice, 3, 2, 2, 1);
        Button btnChooseCombination = new Button("Vælg kombination");
        pane.add(btnChooseCombination, 3, 3, 2, 1);
        btnChooseCombination.setDisable(true);

        yatzySelector = new YatzySelector();
        yatzySelector.addRadioButtons();
        ToggleGroup tg = new ToggleGroup();
        for (int i = 0; i < 15; i++) {
            yatzySelector.getRadioButtons()[i].setToggleGroup(tg);
            if (i < 6) {
                pane.add(yatzySelector.getRadioButtons()[i], 0, 5 + i, 2, 1);
            } else {
                pane.add(yatzySelector.getRadioButtons()[i], 0, 5 + i + 2, 2, 1);
            }
        }
        int[] possibleScores = yatzyResultCalculator.getScores();
        for (int i = 0; i < txtfsPossibleScores.length; i++) {
            txtfsPossibleScores[i] = new TextField(Integer.toString(possibleScores[i]));
            txtfsPossibleScores[i].setMaxWidth(45);
            if (i < 6) {
                pane.add(txtfsPossibleScores[i], 2, 5 + i);
            } else pane.add(txtfsPossibleScores[i], 2, 5 + i + 2);
        }
        Label lblSum = new Label("Sum");
        Label lblBonus = new Label("Bonus");
        Label lblTotal = new Label("Total");
        TextField txtfSum = new TextField("0");
        txtfSum.setMaxWidth(45);
        TextField txtfBonus = new TextField("0");
        txtfBonus.setMaxWidth(45);
        TextField txtfTotal = new TextField("0");
        txtfTotal.setMaxWidth(45);
        pane.add(lblSum, 4, 11);
        pane.add(lblBonus, 4, 12);
        pane.add(lblTotal, 4, 22);
        pane.add(txtfSum, 5, 11);
        pane.add(txtfBonus, 5, 12);
        pane.add(txtfTotal, 5, 22);

        btnThrowDice.setOnAction(event -> {

                    throwsLeft -= 1;
                    lblThrowsLeftInt.setText(Integer.toString(throwsLeft));
                    for (int i = 0; i < 5; i++) {
                        booleans[i] = checkBoxes[i].isSelected();
                    }
                    rc1.throwSomeDice(booleans);
                    newIntDice(intDice, rc1.getDiceToInt());
                    for (int i = 0; i < 5; i++) {
                        pane.add(diceImage(intDice[i] - 1), i, 0);
                    }
                    for (int i = 0; i < txtfsPossibleScores.length; i++) {
                        txtfsPossibleScores[i].setText(Integer.toString(yatzyResultCalculator.getScores()[i]));
                    }

                    if (throwsLeft == 0) {
                        btnThrowDice.setDisable(true);
                        btnChooseCombination.setDisable(false);
                    }
                    Label lblScore = new Label();
                    btnChooseCombination.setOnAction(event1 -> {
                        for (int i = 0; i < yatzySelector.getRadioButtons().length; i++) {
                            if (yatzySelector.getRadioButtons()[i].isSelected()){
                                btnChooseCombination.setDisable(true);
                                btnThrowDice.setDisable(false);
                                throwsLeft=3;
                                lblThrowsLeftInt.setText(Integer.toString(throwsLeft));
                                yatzySelector.getRadioButtons()[i].setSelected(false);
                                yatzySelector.getRadioButtons()[i].setDisable(true);
                                lblScore.setText(txtfsPossibleScores[i].getText());
                                if (i < 6) {
                                    pane.add(lblScore, 3, 5 + i);
                                    String sSum = Integer.toString(Integer.valueOf(txtfSum.getText())+Integer.valueOf(txtfsPossibleScores[i].getText()));
                                    String tSum = Integer.toString(Integer.valueOf(txtfTotal.getText())+Integer.valueOf(txtfsPossibleScores[i].getText()));
                                    txtfSum.setText(sSum);
                                    txtfTotal.setText(tSum);
                                    if (Integer.parseInt(sSum)>=63){
                                        txtfBonus.setText("50");
                                    }
                                } else pane.add(lblScore, 3, 5 + i + 2);
                            }

                        }

                    });


                }
        );


    }

    //this method returns an ImageView with an image of the die rolled
    public ImageView diceImage(int dieEyes) {
        URL urlD1 = YatzyGui.class.getResource("images/Dice-1.png");
        URL urlD2 = YatzyGui.class.getResource("images/Dice-2.png");
        URL urlD3 = YatzyGui.class.getResource("images/Dice-3.png");
        URL urlD4 = YatzyGui.class.getResource("images/Dice-4.png");
        URL urlD5 = YatzyGui.class.getResource("images/Dice-5.png");
        URL urlD6 = YatzyGui.class.getResource("images/Dice-6.png");
        Image imageD1 = new Image(urlD1.toString());
        Image imageD2 = new Image(urlD2.toString());
        Image imageD3 = new Image(urlD3.toString());
        Image imageD4 = new Image(urlD4.toString());
        Image imageD5 = new Image(urlD5.toString());
        Image imageD6 = new Image(urlD6.toString());
        ImageView[] imageViews = new ImageView[6];
        for (int i = 0; i < imageViews.length; i++) {
            imageViews[i] = new ImageView();
            imageViews[i].setFitHeight(40);
            imageViews[i].setPreserveRatio(true);
        }
        imageViews[0].setImage(imageD1);
        imageViews[1].setImage(imageD2);
        imageViews[2].setImage(imageD3);
        imageViews[3].setImage(imageD4);
        imageViews[4].setImage(imageD5);
        imageViews[5].setImage(imageD6);
        //If we roll a 6 the image looks better if rotated 90 degrees.
        imageViews[5].setRotate(90);
        return imageViews[dieEyes];
    }

    public void newIntDice(int[] pInts, int[] nints) {
        for (int i = 0; i < pInts.length; i++) {
            pInts[i] = nints[i];
        }
    }
}
