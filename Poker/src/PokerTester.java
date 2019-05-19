import java.io.IOException;
import java.util.Arrays;

public class PokerTester {
    public static void main(String[] args) throws IOException {
        double[] playerCredit = new double[Constants.numOfPlayers];
        Arrays.fill(playerCredit, Constants.startingAmount);

        boolean keepPlaying = true;
        while(keepPlaying) {
            playerCredit = game(playerCredit);
            for (double aNewCred : playerCredit) {
                if (aNewCred < 0) {
                    keepPlaying = false;
                }
            }
        }

        int[][] a = Compare.findSets();
        System.out.println(Arrays.deepToString(a));

        System.out.println();
        for(int i = 0; i < Constants.numOfPlayers; i++){
            System.out.println(Arrays.toString(Compare.Sort()[i]));
        }
    }
    private static double[] game(double[] playerCredit) throws IOException {
        String[] ranks = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        String[] suits = {"♥", "♣", "♦", "♠"};
        int[] pointValues = {14, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
        boolean keepPlaying = true;

        new Deck(ranks, suits, pointValues, CardMaker.constructCard());

        double[][] allBets = new double[Constants.numOfSubRounds][Constants.numOfPlayers];

        String[][] tableR1 = new String[3][5];
        String[][] tableR2 = new String[4][5];
        boolean[][] folded = new boolean[3][Constants.numOfPlayers];

        for (int i = 0; i < Deck.giveTableCards().length; i++) {
            if (i < 3) {
                tableR1[i] = Deck.giveTableCards()[i];
            }
            if (i < 4) {
                tableR2[i] = Deck.giveTableCards()[i];
            }

        }
        String[][][] tableAllRound = {tableR1, tableR2, Deck.giveTableCards()};

        CardMaker.printCard(CardMaker.constructCard());
        CardMaker.printCard(Deck.givePrint());
        for (int i = 1; i <= Constants.numOfPlayers; i++) {
            System.out.println("Player " + i + " cards");
            CardMaker.printCard(Deck.playerCards(i));
            System.in.read();
            for (int j = 0; j < 20; j++) {
                System.out.println();
            }
            if (i < Constants.numOfPlayers)
                System.out.println("Please pass to player " + (i + 1));
            else
                System.out.println("Please press enter to start game.");
            System.in.read();
            for (int j = 0; j < 20; j++) {
                System.out.println();
            }
        }
        System.out.println("Every player starts with $" + Constants.startingAmount);
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
                if (allBet[j] > 0) {
                    sum += allBet[j];
                    playerCredit[j] -= allBet[j];
                }
            }
        }
        /*
         * for(int i = 0; i < Constants.numOfPlayers){
         *     if(player[i] == winner){
         *        playerCredit[i] += sum;
         *     }
         * }
         * */
        System.out.println(sum);
        System.out.println(Arrays.toString(playerCredit));
        System.out.println(Arrays.deepToString(folded));
        for(int i = 0; i < 30; i++){
            System.out.println();
        }
        for (double aNewCred : playerCredit) {
            if (aNewCred < 0) {
                keepPlaying = false;
            }
        }
        if(keepPlaying){
            System.out.println("Please pass to player one.");
            System.in.read();
        }else{
            System.out.println("Thanks for playing, unfortunately one or more players has run out of money.");
        }
        return playerCredit;
    }
}
