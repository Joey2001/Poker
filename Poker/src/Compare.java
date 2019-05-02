class Compare {
    static Card[][] getData(){
        Card[][] cards = new Card[Constants.numOfPlayers][7];
        for(int i = 0; i < cards.length; i++){
            cards[i] = Deck.passPlayerXTable(i + 1);
        }
        return cards;
    }

    static boolean[][][][] checkPoint() {
        Card[][] players = new Card[Constants.numOfPlayers][Deck.playerCards(1).length];
        Card[] table = new Card[Deck.cardsUsed().length - (Constants.numOfPlayers * 2)];
        int v = 0;
        for (int t = 0; t < players.length; t++) {
            for (int u = 0; u < players[0].length; u++) {
                players[t][u] = Deck.cardsUsed()[v];
                v++;
            }
        }
        if (table.length >= 0) System.arraycopy(Deck.cardsUsed(), (Constants.numOfPlayers * 2), table, 0, table.length);
        boolean[][][][] checkCards = new boolean[Constants.numOfPlayers][2][2][table.length];

        for (int i = 0; i < Constants.numOfPlayers; i++) {
            for (int j = 0; j < table.length; j++) {
                for (int k = 0; k < 2; k++) {
                    checkCards[i][k][0][j] = Card.CompareSuit(players[i][k], table[j]);
                    checkCards[i][k][1][j] = Card.CompareVal(players[i][k], table[j]);
                }
            }
        }
        return checkCards;
    }
    static Card[][] Sort(){
        Card[][] arr = getData();

        for(int a = 0; a < arr.length; a++) {
            for (int i = 0; i < arr[0].length; i++) {
                int min = i;
                for (int j = i + 1; j < arr[0].length; j++) {
                    if (arr[a][j].Value() < arr[a][min].Value())
                        min = j;
                }
                Card temp = arr[a][min];
                arr[a][min] = arr[a][i];
                arr[a][i] = temp;
            }
        }
        return arr;
    }
}
