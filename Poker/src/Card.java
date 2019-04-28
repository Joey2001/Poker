public class Card {
    private String suit;
    private String rank;
    private int value;

    Card(String rank, String suit, int value){
        this.suit = suit;
        this.rank = rank;
        this.value = value;
    }

    private String Suit(){
        return suit;
    }

//    String Rank(){
//        return rank;
//    }

    private int Value(){
        return value;
    }

//    boolean Compare(Card card){
//        boolean cardSuit = card.suit.equals(this.suit);
//        boolean cardRank = card.rank.equals(this.rank);
//        boolean cardValue = card.value == this.value;
//        return cardSuit && cardRank && cardValue;
//
//    }

    static boolean CompareVal(Card a, Card b){
        return a.Value() == b.Value();
    }
    static boolean CompareSuit(Card a, Card b){
        return a.Suit().equals(b.Suit());
    }

    @Override
    public String toString(){
        return rank + " of " + suit + " (point value = " + value + ")";
    }
}
