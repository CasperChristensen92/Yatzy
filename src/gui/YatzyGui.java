package gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import models.RaffleCup;

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

    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setGridLinesVisible(true);
        RaffleCup rc1 = new RaffleCup();
        Label lbd1 = new Label(String.valueOf(rc1.getDice()[0].getEyes()));
        Label lbd2 = new Label(String.valueOf(rc1.getDice()[1].getEyes()));
        lbd1.setBorder();
    }


    private void initContent2(GridPane pane) {
        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setGridLinesVisible(true);

        ToggleGroup tg = new ToggleGroup();

        RadioButton rbOnes = new RadioButton("1'ere");
        RadioButton rbTwos = new RadioButton("2'ere");
        RadioButton rbThrees = new RadioButton("3'ere");
        RadioButton rbFours = new RadioButton("4'ere");
        RadioButton rbFives = new RadioButton("5'ere");
        RadioButton rbSixes = new RadioButton("6'ere");

        RadioButton rbOnePair = new RadioButton("Et par");
        RadioButton rbTwoPairs = new RadioButton("To par");
        RadioButton rbThreeKind = new RadioButton("3 ens");
        RadioButton rbFourKind = new RadioButton("4 ens");
        RadioButton rbSmallStraight = new RadioButton("Lille straight");
        RadioButton rbBigStraight = new RadioButton("Store straight");
        RadioButton rbFullHouse = new RadioButton("Fuldt hus");
        RadioButton rbChance = new RadioButton("Chance");
        RadioButton rbYatzy = new RadioButton("Yatzy");

    }
}
