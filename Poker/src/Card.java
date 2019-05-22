public class Card {
    //initializes internal variables that are passed to the card class
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

    //    returns the value of the suit
    String Suit(){
        return suit;
    }

    //    returns value of the card
    int Value(){
        return value;
    }

    //    returns the string array of the card to print
    String[] Print(){
        return prints;
    }

    @Override
    public String toString(){
        return rank + " of " + suit + " (point value = " + value + ")" /*+ Arrays.toString(prints)*/;
    }
}
