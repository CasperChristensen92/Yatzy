package models;

public class RaffleCup {
    private Die[] dice = new Die[5];

    public RaffleCup() {
        // Starts the rafflecup by throwing the dice
        throwDice();
    }

    public void throwDice() {
        //
        for (Die die : dice){
            die.roll();
        }
    }

    public Die[] getDice() {
        return dice;
    }


}
