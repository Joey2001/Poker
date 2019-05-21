import java.util.Scanner;
import java.util.regex.Pattern;

class BetLogic {
    static double[] Betting(boolean[][] folding){
        double highestBid = -1;
        double pot = 0;
        int playersIn = Constants.numOfPlayers;

        double[] playersBet = new double[Constants.numOfPlayers];
        boolean[] playerFold = new boolean[Constants.numOfPlayers];
        for (boolean[] aFolding : folding) {
            for (int j = 0; j < folding[0].length; j++) {
                if (aFolding[j]) {
                    playerFold[j] = true;
                }
            }
        }
        for (boolean aPlayerFold : playerFold) {
            if (aPlayerFold) {
                playersIn--;
            }
        }
        for(int j = 0; j < playersBet.length; j++){
            if (!playerFold[j]) {
                playersBet[j] = betting(j);
                if(playersBet[j] > highestBid)
                    highestBid = playersBet[j];
            }
        }
        for (double aPlayersBet : playersBet) {
            if (aPlayersBet > 0) {
                pot += aPlayersBet;
            }
        }
        raiseBet(highestBid, playersBet, playerFold, pot, playersIn);

        return playersBet;
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
        if (!Pattern.matches("[a-zA-Z]+", bets) && Pattern.matches("[0-9]+", bets) && bets.length() < 6)
            return Math.abs(Double.parseDouble(bets));
        return -.0001;
    }
}
