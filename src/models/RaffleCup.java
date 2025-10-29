package models;

public class RaffleCup {
    private Die[] dice = new Die[5];

    public RaffleCup() {
        // creates 5 dice and initiates the rafflecup by throwing the dice once
        for (int i = 0; i < 5; i++) {
            dice[i] = new Die();
            }
        throwDice();
    }

    public void throwDice() {
        //
            for (Die die : dice){
            die.roll();
        }
    }

    public void throwSomeDice(boolean[] booleans){
        for (int i = 0; i < 5; i++) {
            if (!booleans[i]) dice[i].roll();
        }
    }

    public Die[] getDice() {
        return dice;
    }

    public int[] getDiceToInt(){
        int[] intDice = new int[5];
        for (int i = 0; i < 5; i++) {
            intDice[i]=dice[i].getEyes();
        }
        return intDice;
    }


}
