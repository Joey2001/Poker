import java.util.ArrayList;

public class Deck {
    private static String[][] printCard;
    private static ArrayList<Card> cards;
    private int deckSize;

    Deck(String[] rank, String[] suit, int[] value){
        cards = new ArrayList<>();

        for(int j = 0; j < rank.length; j++){
            for (String aSuit : suit) {
                Card aCard = new Card(rank[j], aSuit, value[j]);
                cards.add(aCard);
            }
        }
        this.deckSize = cards.size();
        printCard = CardMaker.constructCard();
        Shuffle();
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

            String[] tempPrn = printCard[r];
            printCard[r] = printCard[k];
            printCard[k] = tempPrn;
        }
    }

    static String[][] giveShuffle(){
        return printCard;
    }

    private static Card[] cardsUsed(){
        Card[] cardData = new Card[((PokerTester.numberOfPlayers * 2) + 5)];
        for(int i = 0; i < cardData.length; i++){
            cardData[i] = cards.get(i);
        }
        return cardData;
    }

    static String[][] playerCards(int playerNum){
        String[][] player = new String[2][PokerTester.numberOfPlayers];
        for(int i = 0; i < player.length; i++){
            player[i] = printCard[(2 * playerNum) - (i + 1)];
        }
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
        boolean[][][][] checkCards = new boolean[PokerTester.numberOfPlayers][table.length][2][2];
        for(int i = 0; i < PokerTester.numberOfPlayers; i++){
            for(int j = 0; j < table.length; j++){
                for(int k = 0; k < 2; k++){
                    checkCards[i][j][k][0] = Card.CompareSuit(players[i][k], table[j]);
                    checkCards[i][j][k][1] = Card.CompareVal(players[i][k], table[j]);
                }
            }
        }
        return checkCards;
    }

    static String[][] giveTableCards(){
        String[][] tableCards = new String[5][printCard[printCard.length - 1].length];
        System.arraycopy(printCard, (PokerTester.numberOfPlayers * 2), tableCards, 0, tableCards.length);
        return tableCards;
    }

    @Override
    public String toString(){
        StringBuilder rtn = new StringBuilder("size = " + deckSize + "\nCards not dealt: \n");

        for (int i = deckSize - 1; i >= 0; i--) {
            rtn.append(cards.get(i));
            if (i != 0) {
                rtn.append(", ");
            }
            if ((deckSize - i) % 2 == 0) {
                rtn.append("\n");
            }
        }

        rtn.append("\nDealt cards: \n");
        for (int i = cards.size() - 1; i >= deckSize; i--) {
            if(i >= 0) {
                rtn.append(cards.get(i));
            }
            if (i != deckSize) {
                rtn.append(", ");
            }
            if ((i - cards.size()) % 2 == 0) {
                rtn.append("\n");
            }
        }

        rtn.append("\n");
        return rtn.toString();
    }
}
