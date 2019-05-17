import java.io.IOException;
import java.util.Arrays;

public class PokerTester {
    public static void main(String[] args) throws IOException {

        String[] ranks = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        String[] suits = {"♥", "♣", "♦", "♠"};
        int[] pointValues = {14, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};

        Deck deck = new Deck(ranks, suits, pointValues, CardMaker.constructCard());

        BetLogic.Betting();

        CardMaker.printCard(CardMaker.constructCard());
        CardMaker.printCard(Deck.givePrint());
        for(int i = 1; i <= Constants.numOfPlayers; i++){
            CardMaker.printCard(Deck.playerCards(i));
        }
        CardMaker.printCard(Deck.giveTableCards());
        System.out.println(Arrays.deepToString(Compare.checkPoint()));
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
