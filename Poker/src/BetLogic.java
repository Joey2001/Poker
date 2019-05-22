import java.util.Scanner;
import java.util.regex.Pattern;

class BetLogic {
    static double[] Betting(boolean[][] folding, double[] balance){
//        initializes needed variables that are used later in the class
        double highestBid = -1;
        double pot = 0;
        int playersIn = Constants.numOfPlayers;

        double[] playersBet = new double[Constants.numOfPlayers];
        boolean[] playerFold = new boolean[Constants.numOfPlayers];
//        takes previous boolean values and checks if the player has folded already
        for (boolean[] aFolding : folding)
            for (int j = 0; j < folding[0].length; j++)
                if (aFolding[j])
                    playerFold[j] = true;
//                updates the number of people who can still bet
        for (boolean aPlayerFold : playerFold)
            if (aPlayerFold)
                playersIn--;
//      goes through each player and checks if the bet is good
//      and updates the highest bid if the player has surpassed the highest bid
        for(int j = 0; j < playersBet.length; j++){
            if (!playerFold[j]) {
                playersBet[j] = betting(j);
                if(balance[j] < playersBet[j]){
                    System.out.println("Please try again.");
                    j--;
                }else if(playersBet[j] > highestBid) {
                    highestBid = playersBet[j];
                }
            }
        }
//      checks if the bet is greater than 0 and adds the bet to the pot
        for (double aPlayersBet : playersBet) {
            if (aPlayersBet > 0) {
                pot += aPlayersBet;
            }
        }
//        starts to run raiseBet and continuously checks if all the players have the same bet
//        and if all the players have folded
        raiseBet(highestBid, playersBet, playerFold, pot, playersIn, balance);

        return playersBet;
    }

    private static void raiseBet(double highestBid, double[] playersBet, boolean[] playerFold, double pot, int playersIn, double[] balance){
//        checks if the players bet all equal each other
        if((pot / playersIn) != highestBid){
//            goes through each player
            for (int j = 0; j < playersBet.length; j++) {
//                checks if the player hasn't folded before
//                and the bet is less than 0
                if(playersBet[j] < 0 && !playerFold[j]) {
                    playerFold[j] = true;
                    playersIn--;
                }
//                checks if the player has a lower bid than the highest bid and if the player hasn't folded before
                if (playersBet[j] < highestBid && !playerFold[j]) {
//                    the betting class is called for the player
                    playersBet[j] = betting(j);
//                    checks if the balance is less than the bet
//                    and the second case checks if the bet is higher than the highest bid
                    if(balance[j] < playersBet[j]){
                        System.out.println("Please try again");
                        j--;
                    }else if(playersBet[j] > highestBid) {
                        highestBid = playersBet[j];
                    }
                }
            }
//            gets all of the bets and adds them to the pot
            pot = 0;
            for (double aPlayersBet : playersBet)
                if (aPlayersBet > 0)
                    pot += aPlayersBet;
//          if the number of the players in is greater than 0
//          then it will run raiseBet again
            if(playersIn != 0)
                raiseBet(highestBid, playersBet, playerFold, pot, playersIn, balance);
        }
    }

    private static double betting(int player){
//        gets the bet amount
        Scanner betAmount = new Scanner(System.in);
        System.out.println("How much do you want to bet player " + (player + 1) + "?");
        String bets = betAmount.next();
//        checks if the string only has numbers and is the quickest and easiest way of doing so
        if (!Pattern.matches("[a-zA-Z]+", bets) && Pattern.matches("[0-9]+", bets) && bets.length() < 6)
            return Math.abs(Double.parseDouble(bets));
        return -.0001;
    }
}
