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
    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(10);

        int[] intDice = rc1.getDiceToInt();

        for (int i = 0; i < 5; i++) {
            pane.add(diceImage(intDice[i]),i,0);
        }
        CheckBox[] checkBoxes = new CheckBox[5];
        for (int i = 0; i < 5; i++) {
            checkBoxes[i] = new CheckBox("Hold");
            pane.add(checkBoxes[i],i,1);
        }

        boolean[] booleans = new boolean[5];

        Label lblThrowsLeft = new Label("Antal kast tilbage:");
        Label lblThrowsLeftInt = new Label(Integer.toString(throwsLeft));

        pane.add(lblThrowsLeft,0,2,2,1);
        pane.add(lblThrowsLeftInt,2,2);
        Button btnThrowDice = new Button("Kast terningerne");
        pane.add(btnThrowDice,3,2,2,1);

        yatzySelector = new YatzySelector();
        yatzySelector.addRadioButtons();
        ToggleGroup tg = new ToggleGroup();
        for (int i = 0; i < 15; i++) {
            yatzySelector.getRadioButtons()[i].setToggleGroup(tg);
            if (i < 6) {
            pane.add(yatzySelector.getRadioButtons()[i], 0, 3 + i);
        }
            else {
                pane.add(yatzySelector.getRadioButtons()[i], 0, 3 + i + 2);
            }
        }




        btnThrowDice.setOnAction(event -> {
                    if (throwsLeft == 0) {
                        btnThrowDice.setDisable(true);
                    }
                    throwsLeft -= 1;
                    lblThrowsLeftInt.setText(Integer.toString(throwsLeft));
                    for (int i = 0; i < 5; i++) {
                        booleans[i] = checkBoxes[i].isSelected();
                    }
                    rc1.throwSomeDice(booleans);
                    newIntDice(intDice, rc1.getDiceToInt());
                    for (int i = 0; i < 5; i++) {
                        pane.add(diceImage(intDice[i]), i, 0);
                        if (throwsLeft == 0) {
                            btnThrowDice.setText("Choose score");
                        } else btnThrowDice.setText("Kast terningerne");
                    }

                }
        );




    }

    //this method returns an ImageView with an image of the die rolled
    public ImageView diceImage (int dieEyes){
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

    public void newIntDice (int[] pInts, int[] nints){
        for (int i = 0; i < pInts.length; i++) {
            pInts[i] = nints[i];
        }
    }


//    private void initContent2(GridPane pane) {
//        pane.setPadding(new Insets(20));
//        pane.setHgap(10);
//        pane.setVgap(10);
//        pane.setGridLinesVisible(true);
//
//        ToggleGroup tg = new ToggleGroup();
//
//        RadioButton rbOnes = new RadioButton("1'ere");
//        RadioButton rbTwos = new RadioButton("2'ere");
//        RadioButton rbThrees = new RadioButton("3'ere");
//        RadioButton rbFours = new RadioButton("4'ere");
//        RadioButton rbFives = new RadioButton("5'ere");
//        RadioButton rbSixes = new RadioButton("6'ere");
//
//
//        RadioButton rbOnePair = new RadioButton("Et par");
//        RadioButton rbTwoPairs = new RadioButton("To par");
//        RadioButton rbThreeKind = new RadioButton("3 ens");
//        RadioButton rbFourKind = new RadioButton("4 ens");
//        RadioButton rbSmallStraight = new RadioButton("Lille straight");
//        RadioButton rbBigStraight = new RadioButton("Store straight");
//        RadioButton rbFullHouse = new RadioButton("Fuldt hus");
//        RadioButton rbChance = new RadioButton("Chance");
//        RadioButton rbYatzy = new RadioButton("Yatzy");
//
//    }
}
