import java.util.Arrays;

public class PokerTester {
    static final int numberOfPlayers = 2;
    public static void main(String[] args){

        String[] ranks = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        String[] suits = {"♥", "♣", "♦", "♠"};
        int[] pointValues = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};

        Deck deck = new Deck(ranks, suits, pointValues, CardMaker.constructCard());

        CardMaker.printCard(CardMaker.constructCard());
        CardMaker.printCard(Deck.givePrint());
        for(int i = 1; i <= numberOfPlayers; i++){
            CardMaker.printCard(Deck.playerCards(i));
        }
        CardMaker.printCard(Deck.giveTableCards());
        System.out.println(Arrays.deepToString(Deck.checkPoint()));
        for(int a = 0; a < Deck.checkPoint().length; a++){
            for(int b = 0; b < Deck.checkPoint()[a].length; b++){
                for(int c = 0; c < Deck.checkPoint()[a][b].length; c++){
                    for(int d = 0; d < Deck.checkPoint()[a][b][c].length; d++){
                        if(Deck.checkPoint()[a][b][c][d]){
                            System.out.print(1);
                        }else{
                            System.out.print(0);
                        }
                    }
                }
            }
        }
    }
}
