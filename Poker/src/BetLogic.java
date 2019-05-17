import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;

class BetLogic {
    static void Betting() throws IOException {
        String output = "";
        double highestBid = -1;
        double pot = 0;

        double[] playersBet = new double[Constants.numOfPlayers];
        boolean[] playerFold = new boolean[Constants.numOfPlayers];
        for(int i = 0; i < playersBet.length; i++){
            playersBet[i] = betting(i);
            if(highestBid < playersBet[i]){
                highestBid = playersBet[i];
            }
        }
        for (double aPlayersBet : playersBet) {
            if (aPlayersBet > 0) {
                pot += aPlayersBet;
            }
        }
        raiseBet(highestBid, playersBet, playerFold, pot, Constants.numOfPlayers);

        for(int i = 0; i < playersBet.length; i++){
            if(playersBet[i] >= 0){
                output += "Player " + (i + 1) + "'s bet was $" + playersBet[i] + "          ";
            }else{
                output += "Player " + (i + 1) + " folded          ";
            }
        }

        BufferedWriter out = new BufferedWriter(new FileWriter("c:/Users/Josep/OneDrive/Documents/outputTest1.txt"));
        out.write(output);
        out.close();

        System.out.println(Arrays.toString(playersBet));
    }

    private static void raiseBet(double highestBid, double[] playersBet, boolean[] playerFold, double pot, int playersIn){
        if((pot / playersIn) != highestBid){
            for (int j = 0; j < playersBet.length; j++) {
                if(playersBet[j] < 0 && !playerFold[j]) {
                    playerFold[j] = true;
                    playersIn--;
                }
                if (playersBet[j] < highestBid && !playerFold[j]) {
                    playersBet[j] = betting(j);
                    if(playersBet[j] > highestBid)
                        highestBid = playersBet[j];
                }
            }
            pot = 0;
            for (double aPlayersBet : playersBet)
                if (aPlayersBet > 0)
                    pot += aPlayersBet;
            if(playersIn != 0)
                raiseBet(highestBid, playersBet, playerFold, pot, playersIn);
        }
    }

    private static double betting(int player){
        Scanner betAmount = new Scanner(System.in);
        System.out.println("How much do you want to bet player " + (player + 1) + "?");
        String bets = betAmount.next();
        if(!Pattern.matches("[a-zA-Z]+", bets)) {
            return Math.abs(Double.parseDouble(bets));
        }else{
            return -2;
        }
    }
}
