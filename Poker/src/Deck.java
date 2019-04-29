import java.util.ArrayList;

public class Deck {
    private static String[][] printCards;
    private static ArrayList<Card> cards;
    private int deckSize;

    Deck(String[] rank, String[] suit, int[] value, String[][] prints){
        cards = new ArrayList<>();

        int z = 0;
        for(int j = 0; j < rank.length; j++) {
            for (String aSuit : suit) {
                Card aCard = new Card(rank[j], aSuit, value[j], prints[z]);
                z++;
                cards.add(aCard);
            }
        }
        this.deckSize = cards.size();
        Shuffle();
        printCards = passPrint();

    }

//    boolean isEmpty(){
//        return cards.size() == 0;
//    }

//    int size(){
//        return cards.size();
//    }

//    Card deal(){
//        if(this.deckSize > 0){
//            this.deckSize--;
//            return cards.get(this.deckSize);
//        }
//        return null;
//    }

    private void Shuffle(){
        for(int k = cards.size() - 1; k >= 0; k--) {
            int r = (int)(Math.random() * k);
            Card tempCard = cards.get(r);
            cards.set(r, cards.get(k));
            cards.set(k, tempCard);
        }
    }

    static String[][] givePrint(){
        return printCards;
    }

    private static String[][] passPrint(){
        String[][] R = new String[52][5];
        for(int i = 0; i < 52; i++)
            R[i] = cards.get(i).Print();
        return R;
    }

    private static Card[] cardsUsed(){
        Card[] cardData = new Card[((PokerTester.numberOfPlayers * 2) + 5)];
        for(int i = 0; i < cardData.length; i++)
            cardData[i] = cards.get(i);
        return cardData;
    }

    static String[][] playerCards(int playerNum){
        String[][][] player = new String[PokerTester.numberOfPlayers][2][5];
        for(int i = player[0].length - 1; i >= 0; i--)
            player[playerNum - 1][1 - i] = cards.get((2 * playerNum) - (i + 1)).Print();
        return player[playerNum - 1];
    }

    static String[][] giveTableCards(){
        String[][] player = new String[5][5];
        for(int i = player[0].length - 1; i >= 0; i--)
            player[i] = cards.get(i + (PokerTester.numberOfPlayers * 2)).Print();
        return player;
    }

    static boolean[][][][] checkPoint(){
        Card[][] players = new Card[PokerTester.numberOfPlayers][playerCards(1).length];
        Card[] table = new Card[cardsUsed().length - (PokerTester.numberOfPlayers * 2)];
        int v = 0;
        for(int t = 0; t < players.length; t++){
            for(int u = 0; u < players[0].length; u++){
                players[t][u] = cardsUsed()[v];
                v++;
            }
        }
        if (table.length >= 0) System.arraycopy(cardsUsed(), (PokerTester.numberOfPlayers * 2), table, 0, table.length);
        boolean[][][][] checkCards = new boolean[PokerTester.numberOfPlayers][2][2][table.length];

        for(int i = 0; i < PokerTester.numberOfPlayers; i++)
            for(int j = 0; j < table.length; j++)
                for(int k = 0; k < 2; k++)
                    for(int l = 0; l < 2; l++)
                        checkCards[i][k][l][j] = Card.CompareSuit(players[i][k], table[j]);
        return checkCards;
    }

    @Override
    public String toString(){
        StringBuilder rtn = new StringBuilder("size = " + deckSize + "\nCards not dealt: \n");

        for (int i = deckSize - 1; i >= 0; i--) {
            rtn.append(cards.get(i));
            if (i != 0)
                rtn.append(", ");
            if ((deckSize - i) % 2 == 0)
                rtn.append("\n");
        }

        rtn.append("\nDealt cards: \n");
        for (int i = cards.size() - 1; i >= deckSize; i--) {
            if(i >= 0)
                rtn.append(cards.get(i));
            if (i != deckSize)
                rtn.append(", ");
            if ((i - cards.size()) % 2 == 0)
                rtn.append("\n");
        }

        rtn.append("\n");
        return rtn.toString();
    }
}
