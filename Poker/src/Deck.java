import java.util.ArrayList;

public class Deck {
    //    initializes internal variables
    private static ArrayList<Card> cards;
    private int deckSize;

    Deck(String[] rank, String[] suit, int[] value, String[][] prints){
//        initializes the cards array list every time the deck class is initialized
        cards = new ArrayList<>();

//        variable to properly store the prints
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

    //    shuffles the cards into a random order
    private void Shuffle(){
//        determines how many times the deck is shuffled
        for(int i = 0; i < Constants.timesToShuffle; i++) {
//            shuffles the cards by switching two cards at a time
            for (int k = cards.size() - 1; k >= 0; k--) {
                int r = (int) (Math.random() * k);
                Card tempCard = cards.get(r);
                cards.set(r, cards.get(k));
                cards.set(k, tempCard);
            }
        }
    }

    //    passes a card array by giving the player number
    static Card[] playerCards(int playerNum){
        Card[] tP = new Card[2];
//        fills the array with the appropriate cards and is done this way to allow for up to 23 players
        for (int i = 1; i >= 0; i--)
            tP[1 - i] = cards.get((2 * playerNum) - (i + 1));
        return tP;
    }

    //    passes the table cards
    static Card[] giveJustTable(){
        Card[] table = new Card[5];
//        fills the array with the appropriate cards and is done this way to allow for up to 23 players
        for(int i = table.length - 1; i >= 0; i--)
            table[i] = cards.get(i + (Constants.numOfPlayers * 2));
        return table;
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
