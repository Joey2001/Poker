import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class PokerTester {
    public static void main(String[] args) throws IOException {
        double[] playerCredit;
        double[] oldCredit;
        ArrayList<String> output = new ArrayList<>();

        Scanner human = new Scanner(System.in);

        int count = 1;

        String chad = "yes";

        System.out.println("Every player starts with $" + Constants.startingAmount);

        System.out.println("How many players do you want?");
        Constants.numOfPlayers = human.nextInt();
        oldCredit = new double[Constants.numOfPlayers];
        playerCredit = new double[Constants.numOfPlayers];

        while(chad.equalsIgnoreCase("yes")) {
            if(count != 1){
                System.out.println("Do you want to change the number of players?");
                String chad2 = human.next();
                if (chad2.equalsIgnoreCase("yes")) {
                    System.out.println("How many players do you want?");
                    Constants.numOfPlayers = human.nextInt();
                    oldCredit = new double[Constants.numOfPlayers];
                    playerCredit = new double[Constants.numOfPlayers];
                }
            }

            Arrays.fill(playerCredit, Constants.startingAmount);
            Arrays.fill(oldCredit, Constants.startingAmount);

            System.out.println("Pass computer to player one");
            System.in.read();
            for(int i = 0; i < Constants.spaces; i++){
                System.out.println();
            }
            output = pushOn(true, playerCredit, oldCredit, output);
            System.out.println("Do you want to play again?");
            chad = human.next();
            output.add("The winner was player " + Compare.winner() + " ending with $" + playerCredit[Compare.winner() - 1]);
            BufferedWriter out = new BufferedWriter(new FileWriter("c:/Users/Josep/OneDrive/Documents/round[" + count + "].txt"));
            out.write(output.toString());
            out.close();
            count++;
        }
    }

    private static ArrayList<String> pushOn(boolean keepPlaying, double[] playerCredit, double[] oldCredit, ArrayList<String> output) throws IOException {
        double[] deltaCredit = new double[oldCredit.length];
        ArrayList<String> temp;
        playerCredit = game(playerCredit);
        for (int i = 0; i < Constants.numOfPlayers; i++) {
            if (playerCredit[i] != -.0001) {
                deltaCredit[i] = oldCredit[i] - playerCredit[i];
            }
        }
        temp = passToFile(deltaCredit);
        for (double aNewCred : playerCredit) {
            if (aNewCred < 0) {
                keepPlaying = false;
            }
        }
        output.addAll(temp);
        oldCredit = playerCredit;
        if(keepPlaying){
            for(int k = 0; k < playerCredit.length; k++){
                System.out.println("Player " + (k + 1) + " has $" + playerCredit[k]);
            }
            System.out.println("Press enter to continue");
            System.in.read();
            for(int i = 0; i < Constants.spaces; i++){
                System.out.println();
            }
            System.out.println("Please pass to player one.");
            System.in.read();
            pushOn(true, playerCredit, oldCredit, output);
        }else{
            System.out.println("Thanks for playing, unfortunately one or more players has run out of money.");
        }
        return output;
    }

    private static double[] game(double[] playerCredit) throws IOException {
        String[] ranks = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        String[] suits = {"♥", "♣", "♦", "♠"};
        int[] pointValues = {14, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};

        new Deck(ranks, suits, pointValues, CardMaker.constructCard());

        double[][] allBets = new double[Constants.numOfSubRounds][Constants.numOfPlayers];

        String[][] tableR1 = new String[3][5];
        String[][] tableR2 = new String[4][5];
        boolean[][] folded = new boolean[3][Constants.numOfPlayers];
        String[][] tableCards = new String[5][5];
        for(int k = 0; k < tableCards.length; k++){
            tableCards[k] = Deck.giveJustTable()[k].Print();
        }

        for (int i = 0; i < tableCards.length; i++) {
            if (i < 3) {
                tableR1[i] = tableCards[i];
            }
            if (i < 4) {
                tableR2[i] = tableCards[i];
            }

        }
        String[][][] tableAllRound = {tableR1, tableR2, tableCards};

        String[][][] printCards = new String[Constants.numOfPlayers][2][5];
        for(int k = 1; k <= Constants.numOfPlayers; k++){
            for(int l = 0; l < 2; l++){
                printCards[k - 1][l] = Deck.playerCards(k)[l].Print();
            }
        }

        for (int i = 1; i <= Constants.numOfPlayers; i++) {
            System.out.println("Player " + i + " cards");
            CardMaker.printCard(printCards[i - 1]);
            System.in.read();
            for (int j = 0; j < Constants.spaces; j++) {
                System.out.println();
            }
            if (i < Constants.numOfPlayers)
                System.out.println("Please pass to player " + (i + 1));
            else
                System.out.println("Please press enter to start game.");
            System.in.read();
            for (int j = 0; j < Constants.spaces; j++) {
                System.out.println();
            }
        }
        for (int j = 0; j < Constants.numOfSubRounds; j++) {
            CardMaker.printCard(tableAllRound[j]);
            double[] betting = BetLogic.Betting(folded);
            for (int i = 0; i < betting.length; i++) {
                if (betting[i] < 0) {
                    folded[j][i] = true;
                }
            }
            allBets[j] = betting;
        }
        double sum = 0;
        for (double[] allBet : allBets) {
            for (int j = 0; j < allBet.length; j++) {
                if (allBet[j] != -.0001) {
                    sum += allBet[j];
                    playerCredit[j] -= allBet[j];
                }
            }
        }
        for(int i = 0; i < Constants.numOfPlayers; i++){
            if(i + 1 == Compare.winner()){
                playerCredit[i] += sum;
            }
        }
        for(int i = 0; i < 30; i++){
            System.out.println();
        }

        return playerCredit;
    }

    private static ArrayList<String> passToFile(double[] bets){
        ArrayList<String> output = new ArrayList<>();
        for (int i = 0; i < bets.length; i++) {
            if (bets[i] >= 0) {
                output.add("Player " + (i + 1) + "'s bet was $" + bets[i] + "          ");
            }
        }
        return output;
    }
}
