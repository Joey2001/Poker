public class PokerTester {
    public static void main(String[] args){

        String[] ranks = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        String[] suits = {"♥", "♣", "♦", "♠"};
        int[] pointValues = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};

        Deck deck = new Deck(ranks, suits, pointValues);

        CardMaker.printCard(CardMaker.constructCard());
        CardMaker.printCard(Deck.giveShuffle());
        CardMaker.printCard(Deck.giveP1Cards());
        CardMaker.printCard(Deck.giveP2Cards());
        CardMaker.printCard(Deck.giveTableCards());
    }
}