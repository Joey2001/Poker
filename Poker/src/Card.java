public class Card {
    private String suit;
    private String rank;
    private int value;
    private String[] prints;

    Card(String rank, String suit, int value, String[] prints){
        this.suit = suit;
        this.rank = rank;
        this.value = value;
        this.prints = prints;
    }

    String Suit(){
        return suit;
    }

    int Value(){
        return value;
    }

    String[] Print(){
        return prints;
    }

    @Override
    public String toString(){
        return rank + " of " + suit + " (point value = " + value + ")" /*+ Arrays.toString(prints)*/;
    }
}
