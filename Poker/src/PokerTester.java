import java.io.IOException;
import java.util.Arrays;

public class PokerTester {
    public static void main(String[] args) throws IOException {
        String[] ranks = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        String[] suits = {"♥", "♣", "♦", "♠"};
        int[] pointValues = {14, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};

        Deck deck = new Deck(ranks, suits, pointValues, CardMaker.constructCard());

        String[][] tableR1 = new String[3][5];
        String[][] tableR2 = new String[4][5];
        boolean[][] folded = new boolean[3][Constants.numOfPlayers];
        for(int i = 0; i < Deck.giveTableCards().length; i++){
            if(i < 3){
                tableR1[i] = Deck.giveTableCards()[i];
            }
            if(i < 4){
                tableR2[i] = Deck.giveTableCards()[i];
            }

        }
        String[][][] tableAllRound = {tableR1, tableR2, Deck.giveTableCards()};
        double[][] allBets = new double[Constants.numOfSubRounds][Constants.numOfPlayers];

        CardMaker.printCard(CardMaker.constructCard());
        CardMaker.printCard(Deck.givePrint());
        for(int i = 1; i <= Constants.numOfPlayers; i++){
            System.out.println("Player " + i + " cards");
            CardMaker.printCard(Deck.playerCards(i));
            System.in.read();
            for(int j = 0; j < 20; j++){
                System.out.println();
            }
            if(i < Constants.numOfPlayers)
                System.out.println("Please pass to player " + (i + 1));
            else
                System.out.println("Please press enter to start game.");
            System.in.read();
            for(int j = 0; j < 20; j++){
                System.out.println();
            }
        }
        for(int j = 0; j < Constants.numOfSubRounds; j++) {
            CardMaker.printCard(tableAllRound[j]);
            double[] betting = BetLogic.Betting(j, folded);
            for(int i = 0; i < betting.length; i++){
                if(betting[i] < 0){
                    folded[j][i] = true;
                }
            }
            allBets[j] = betting;
        }
        System.out.println(Arrays.deepToString(folded));
        for(int a = 0; a < Compare.checkPoint().length; a++){
            for(int b = 0; b < Compare.checkPoint()[a].length; b++){
                for(int c = 0; c < Compare.checkPoint()[a][b].length; c++){
                    for(int d = 0; d < Compare.checkPoint()[a][b][c].length; d++){
                        if(Compare.checkPoint()[a][b][c][d]){
                            System.out.print(1);
                        }else{
                            System.out.print(0);
                        }
                    }
                }
            }
        }
        System.out.println();
        for(int i = 0; i < Constants.numOfPlayers; i++){
            System.out.println(Arrays.toString(Compare.Sort()[i]));
        }
    }
}
