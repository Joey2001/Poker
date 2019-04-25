public class Card {
    private String suit;
    private String rank;
    private int value;

    Card(String rank, String suit, int value){
        this.suit = suit;
        this.rank = rank;
        this.value = value;
    }

    String Suit(){
        return suit;
    }

    String Rank(){
        return rank;
    }

    int Value(){
        return value;
    }

    boolean Compare(Card card){
        boolean cardSuit = card.suit.equals(this.suit);
        boolean cardRank = card.rank.equals(this.rank);
        boolean cardValue = card.value == this.value;
        return cardSuit && cardRank && cardValue;

    }

    @Override
    public String toString(){
        return rank + " of " + suit + " (point value = " + value + ")";
    }
}