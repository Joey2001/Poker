import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class PokerTester {
    public static void main(String[] args) throws IOException {
//        initializing an array for player credit and the array that stores the previous credit
        double[] playerCredit;
        double[] oldCredit;
//        initializing an array list for the ouput to put into a txt file
        ArrayList<String> output = new ArrayList<>();

//        initializing the scanner used throughout this class
        Scanner human = new Scanner(System.in);

//        count is used to differentiate between different games
        int count = 1;

        String chad = "yes";

        System.out.println("Every player starts with $" + Constants.startingAmount);

//        user gets to choose how many players there are
        System.out.println("How many players do you want?");
        Constants.numOfPlayers = human.nextInt();
        oldCredit = new double[Constants.numOfPlayers];
        playerCredit = new double[Constants.numOfPlayers];

//        while the players want to continue, the players say yes
        while(chad.equalsIgnoreCase("yes")) {
//            resets everything if you want the number of players to change
            if(count != 1){
                System.out.println("Do you want to change the number of players?");
                String chad2 = human.next();
                if (chad2.equalsIgnoreCase("yes")) {
                    System.out.println("How many players do you want?");
                    Constants.numOfPlayers = human.nextInt();
                    oldCredit = new double[Constants.numOfPlayers];
                    playerCredit = new double[Constants.numOfPlayers];
                }
            }

//            fills the arrays back to the original amount
            Arrays.fill(playerCredit, Constants.startingAmount);
            Arrays.fill(oldCredit, Constants.startingAmount);

            System.out.println("Pass computer to player one");
            System.in.read();
//            fills the console with spaces so all the players only see their cards
            for(int i = 0; i < Constants.spaces; i++){
                System.out.println();
            }
//            runs the pushOn method and returns the array list to output
            output = pushOn(true, playerCredit, oldCredit, output);
//            check if the players want to go again
            System.out.println("Do you want to play again?");
            chad = human.next();
//            all of this is dedicated to outputting to a txt file to view the results later
            output.add("The winner this round was player " + Compare.winner());
            BufferedWriter out = new BufferedWriter(new FileWriter("c:/Users/Josep/OneDrive/Documents/round[" + count + "].txt"));
            out.write(output.toString());
            out.close();
//            increments count to create a new file every time that the outside while loop runs
            count++;
        }
    }

    private static ArrayList<String> pushOn(boolean keepPlaying, double[] playerCredit, double[] oldCredit, ArrayList<String> output) throws IOException {

        double[] deltaCredit = new double[oldCredit.length];
        ArrayList<String> temp;
        playerCredit = game(playerCredit);
//        finds the difference between the last credit versus this credit
        for (int i = 0; i < Constants.numOfPlayers; i++) {
            if (playerCredit[i] != -.0001) {
                deltaCredit[i] = oldCredit[i] - playerCredit[i];
            }
        }
//        temp is used to get the array list from the bets and eventually the output
        temp = passToFile(deltaCredit);
//        checks if the credit is 0 or greater to continue the game
        for (double aNewCred : playerCredit)
            keepPlaying = aNewCred >= 0;
//        adds the entire temp array list to the output to eventually get to a txt file
        output.addAll(temp);
//        old credit is now the player credit because player credit will change
//        and we want to find the current change in credit
        oldCredit = playerCredit;
        if(keepPlaying){
//            prints out how much money each player has left
            for(int k = 0; k < playerCredit.length; k++){
                System.out.println("Player " + (k + 1) + " has $" + playerCredit[k]);
            }
            System.out.println("Press enter to continue");
            System.in.read();
            for(int i = 0; i < Constants.spaces; i++){
                System.out.println();
            }
            System.out.println("Please pass to player one.");
            System.in.read();
//            calls itself to continue on and keep playing the game
            pushOn(true, playerCredit, oldCredit, output);
        }else{
//            if keepPlaying is false, it will stop the recursion and return this message;
            System.out.println("Thanks for playing, unfortunately one or more players has run out of money.");
        }
        return output;
    }

    private static double[] game(double[] playerCredit) throws IOException {
//        initializes the ranks, suits, and point values here
        String[] ranks = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        String[] suits = {"♥", "♣", "♦", "♠"};
        int[] pointValues = {14, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};

//        calls the Deck class to construct and shuffle the cards
        new Deck(ranks, suits, pointValues, CardMaker.constructCard());

//        contains all of the bets from the entire round
        double[][] allBets = new double[Constants.numOfSubRounds][Constants.numOfPlayers];

//        constructs and fills two more 2D String arrays to print out the correct table
//        and make it look like an actual game of Poker as much as possible
        String[][] tableR1 = new String[3][5];
        String[][] tableR2 = new String[4][5];
        boolean[][] folded = new boolean[3][Constants.numOfPlayers];
        String[][] tableCards = new String[5][5];
        for(int k = 0; k < tableCards.length; k++){
            tableCards[k] = Deck.giveJustTable()[k].Print();
        }

        for (int i = 0; i < tableCards.length; i++) {
            if (i < 3) {
                tableR1[i] = tableCards[i];
            }
            if (i < 4) {
                tableR2[i] = tableCards[i];
            }

        }
//        initializes a 3D string array as all of the table states
        String[][][] tableAllRound = {tableR1, tableR2, tableCards};

//        initializes and constructs a 3D string array for all of the players to see their card
        String[][][] printCards = new String[Constants.numOfPlayers][2][5];
        for(int k = 1; k <= Constants.numOfPlayers; k++){
            for(int l = 0; l < 2; l++){
                printCards[k - 1][l] = Deck.playerCards(k)[l].Print();
            }
        }

//        starts the game with the players being shown their cards
        for (int i = 1; i <= Constants.numOfPlayers; i++) {
            System.out.println("Player " + i + " cards");
            CardMaker.printCard(printCards[i - 1]);
            System.in.read();
            for (int j = 0; j < Constants.spaces; j++) {
                System.out.println();
            }
            if (i < Constants.numOfPlayers)
                System.out.println("Please pass to player " + (i + 1));
            else
                System.out.println("Please press enter to start game.");
            System.in.read();
            for (int j = 0; j < Constants.spaces; j++) {
                System.out.println();
            }
        }
//        repeats everything in the for loop necessary for the three sub rounds to happen
        for (int j = 0; j < Constants.numOfSubRounds; j++) {
//            prints the necessary table state for the round
            CardMaker.printCard(tableAllRound[j]);
//            betting is called and stored in the betting array
            double[] betting = BetLogic.Betting(folded, playerCredit);
//            this checks if each player has folded or not
            for (int i = 0; i < betting.length; i++)
                folded[j][i] = betting[i] >= 0;
//            sets the betting array to part of the 2D allBets array
            allBets[j] = betting;
        }
//        sum just finds the total amount of money is in the pot and takes
//        an appropriate amount from each player
        double sum = 0;
        for (double[] allBet : allBets) {
            for (int j = 0; j < allBet.length; j++) {
                if (allBet[j] != -.0001) {
                    sum += allBet[j];
                    playerCredit[j] -= allBet[j];
                }
            }
        }
//        this part finds who the winner is and gives them the pot
        for(int i = 1; i <= Constants.numOfPlayers; i++){
            if(i == Compare.winner()){
                playerCredit[i - 1] += sum;
            }
        }
//        adds spaces
        for(int i = 0; i < 30; i++){
            System.out.println();
        }

//        gives playerCredit to the double array made in another method
        return playerCredit;
    }

    private static ArrayList<String> passToFile(double[] bets){
//        takes bet and puts the player bet in an array list to be passed to a txt file later
        ArrayList<String> output = new ArrayList<>();
        for (int i = 0; i < bets.length; i++) {
            if (bets[i] >= 0) {
                output.add("Player " + (i + 1) + "'s bet was $" + bets[i] + "          ");
            }
        }
        return output;
    }
}
