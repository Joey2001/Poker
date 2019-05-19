import java.util.ArrayList;

public class Deck {
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

    }

    private void Shuffle(){
        for(int i = 0; i < Constants.timesToShuffle; i++) {
            for (int k = cards.size() - 1; k >= 0; k--) {
                int r = (int) (Math.random() * k);
                Card tempCard = cards.get(r);
                cards.set(r, cards.get(k));
                cards.set(k, tempCard);
            }
        }
    }

    static String[][] playerCards(int playerNum){
        String[][][] player = new String[Constants.numOfPlayers][2][5];
        for(int i = player[0].length - 1; i >= 0; i--)
            player[playerNum - 1][1 - i] = cards.get((2 * playerNum) - (i + 1)).Print();
        return player[playerNum - 1];
    }

    static Card[] playerX(int playerNum){
        Card[] tP = new Card[2];
        for (int i = 1; i >= 0; i--)
            tP[1 - i] = cards.get((2 * playerNum) - (i + 1));
        return tP;
    }

    static String[][] giveTableCards(){
        String[][] table = new String[5][5];
        for(int i = table[0].length - 1; i >= 0; i--)
            table[i] = cards.get(i + (Constants.numOfPlayers * 2)).Print();
        return table;
    }

    static Card[] giveJustTable(){
        Card[] table = new Card[5];
        for(int i = table.length - 1; i >= 0; i--)
            table[i] = cards.get(i + (Constants.numOfPlayers * 2));
        return table;
    }

    static Card[] passPlayerXTable(int playerNum){
        Card[] tP = new Card[7];
        for (int i = 1; i >= 0; i--)
            tP[1 - i] = cards.get((2 * playerNum) - (i + 1));
        for (int j = 2; j < tP.length; j++)
            tP[j] = cards.get((j - 2) + (Constants.numOfPlayers * 2));
        return tP;
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
