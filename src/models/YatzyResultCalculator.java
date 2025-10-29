package models;

import java.util.Arrays;

/**
 * Used to calculate the score of throws with 5 dice
 */
public class YatzyResultCalculator {
    private Die[] dice;
    private int[] sortedIntDice;
    /**
     *
     * @param dice
     */
    public YatzyResultCalculator(Die[] dice) {
        this.dice = dice;
        // Saves dice in int array and sorts the array to use for later methods.
        this.sortedIntDice = diceToInt();
        Arrays.sort(this.sortedIntDice);
    }

    public void setDice(Die[] dice) {
        this.dice = dice;
    }

    public void setSortedIntDice() {
        this.sortedIntDice = diceToInt();
        Arrays.sort(this.sortedIntDice);
    }

    /**
     * Calculates the score for Yatzy uppersection
     * @param eyes eye value to calculate score for. eyes should be between 1 and 6
     * @return the score for specified eye value
     */
    public int upperSectionScore(int eyes) {
        //TODO: Implement upperSectionScore method.
        int sum = 0;
        for (int i = 0; i < sortedIntDice.length; i++) {
            //Because the array is sorted we return the sum if the value is greater than the one we are looking for
            if (sortedIntDice[i]>eyes){
                return sum;
            } else if (sortedIntDice[i]==eyes) {
                sum+=eyes;
            }
        }
        return sum;
    }

    public int onePairScore() {
        //The array of numbers is sorted so if we check from the top we can return the first pair we find
        int lastValue = sortedIntDice[sortedIntDice.length-1];
        for (int i = sortedIntDice.length-2; i >= 0; i--) {
            int newValue = sortedIntDice[i];
            if (lastValue == newValue){
                return newValue*2;
            }
            lastValue = newValue;
        }
        return 0;
    }

    public int twoPairScore() {
        int firstPair = 0;
        int secondPair = 0;
        int lastValue = 0;
        for (int x : sortedIntDice){
            if (x == lastValue){
                if (firstPair!=x) {
                    secondPair = firstPair;
                    firstPair = x;
                }
            }
            lastValue=x;
        }
        if (secondPair > 0 && firstPair!=secondPair){
            return (firstPair*2)+secondPair*2;
        }
        return 0;
    }

    public int threeOfAKindScore() {
        //
        int firstValue = sortedIntDice[0];
        int secondValue = sortedIntDice[1];
        for (int i = 2; i < sortedIntDice.length; i++) {
            if (secondValue == sortedIntDice[i]){
                if (firstValue == sortedIntDice[i])
                    return sortedIntDice[i]*3;
                else firstValue =sortedIntDice[i];
            }
            else{
                firstValue=secondValue;
                secondValue = sortedIntDice[i];
            }
        }
        return 0;
    }

    public int fourOfAKindScore() {
        if (sortedIntDice[0]==sortedIntDice[3]) return sortedIntDice[0]*4;
        if (sortedIntDice[1]==sortedIntDice[4]) return sortedIntDice[1]*4;
        return 0;
    }

    public int smallStraightScore() {
        int[] smallStraightChecker = {1,2,3,4,5};
        if (Arrays.equals(smallStraightChecker, sortedIntDice)) return 15;
        //TODO: implement smallStraightScore method.
        return 0;
    }

    public int largeStraightScore() {
        int[] largeStraightChecker = {2,3,4,5,6};
        if (Arrays.equals(largeStraightChecker,sortedIntDice)) return 20;
        //TODO: implement largeStraightScore method.
        return 0;
    }

    public int fullHouseScore() {
        //TODO: implement fullHouseScore method.
        if (threeOfAKindScore()!=0){
            if (onePairScore()*1.5!=threeOfAKindScore() && onePairScore()!=0){
                return threeOfAKindScore()+onePairScore();
            }
            if (smallPairScore()*1.5!= threeOfAKindScore() && smallPairScore()!=0){
                return threeOfAKindScore()+ smallPairScore();
            }
        }
        return 0;
    }

    public int chanceScore() {
        //TODO: implement chanceScore method.
        int sum=0;
        for (int x : sortedIntDice){
            sum+=x;
        }
        return sum;
    }

    public int yatzyScore() {
        if (sortedIntDice[0]==sortedIntDice[4]){
            return 50;
        }
        //TODO: implement yatzyScore method.
        return 0;
    }

    public int[] diceToInt() {
        int[] intDice = new int[5];
        for (int i = 0; i < 5; i++) {
            intDice[i]=dice[i].getEyes();
        }
        return intDice;
    }

    public int smallPairScore(){
        //We find the smallest of two possible pairs to use in other methods
        int lastValue = sortedIntDice[0];
        for (int i = 1; i < sortedIntDice.length; i++) {
            int newValue = sortedIntDice[i];
            if (lastValue == newValue){
                return newValue*2;
            }
            lastValue = newValue;
        }
        return 0;
    }
    //We have stored stuff in arrays in the other classes and this returns the wanted calculation
    public int[] getScores(){
        sortedIntDice = diceToInt();
        Arrays.sort(sortedIntDice);
        int[] scoreArray = new int[15];
        for (int i = 0; i < 6; i++) {
            scoreArray[i]=upperSectionScore(i+1);
        }
        scoreArray[6]=onePairScore();
        scoreArray[7]= twoPairScore();
        scoreArray[8]= threeOfAKindScore();
        scoreArray[9]= fourOfAKindScore();
        scoreArray[10] = smallStraightScore();
        scoreArray[11] = largeStraightScore();
        scoreArray[12] = fullHouseScore();
        scoreArray[13] = chanceScore();
        scoreArray[14] = yatzyScore();
        return scoreArray;
    }

        //
}
