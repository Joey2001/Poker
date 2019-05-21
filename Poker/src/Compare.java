import java.util.ArrayList;

class Compare {

//    finds if five cards in succession are going up by one as the sorted list is checked
    private static boolean[] detectStraight(){
        Card[][] sortedCard = Sort();
        boolean[][] a = new boolean[5][Constants.numOfPlayers];
//        goes through all of the players to check for the straight or a flush
        for(int i = 0; i < a[0].length; i++){
            int cardOne = sortedCard[i][0].Value();
            int cardTwo = sortedCard[i][1].Value();
            int cardThree = sortedCard[i][2].Value();
            a[0][i] = sortedCard[i][1].Value() == (cardOne + 1) && sortedCard[i][2].Value() == (cardOne + 2) && sortedCard[i][3].Value() == (cardOne + 3) && sortedCard[i][4].Value() == (cardOne + 4);
            a[1][i] = sortedCard[i][2].Value() == (cardTwo + 1) && sortedCard[i][3].Value() == (cardTwo + 2) && sortedCard[i][4].Value() == (cardTwo + 3) && sortedCard[i][5].Value() == (cardTwo + 4);
            a[2][i] = sortedCard[i][3].Value() == (cardThree + 1) && sortedCard[i][4].Value() == (cardThree + 2) && sortedCard[i][5].Value() == (cardThree + 3) && sortedCard[i][6].Value() == (cardThree + 4);
            if(sortedCard[i][6].Value() == 14 && cardOne == 2){
                a[3][i] = cardTwo == 3 && cardThree == 4 && sortedCard[i][3].Value() == 5;
            }
        }
//        determines if one of the values is a flush and passes only one boolean per player
        for(int i = 0; i < a.length; i++){
            for(int j = 0; j < Constants.numOfPlayers; j++){
                if(a[i][j]){
                    a[4][j] = true;
                }
            }
        }
        return a[4];
    }

//    takes the data from detectStraight to determine if a flush is found with all the suits matching
    private static boolean[] detectStraightFlush(){
        Card[][] sortedCard = Sort();
        boolean[] flush = detectStraight();
        boolean[][] a = new boolean[5][Constants.numOfPlayers];
        for(int i = 0; i < a[0].length; i++){
            String cardOne = sortedCard[i][0].Suit();
            String cardTwo = sortedCard[i][1].Suit();
            String cardThree = sortedCard[i][2].Suit();
            a[0][i] = sortedCard[i][1].Suit().equals(cardOne) && sortedCard[i][2].Suit().equals(cardOne) && sortedCard[i][3].Suit().equals(cardOne) && sortedCard[i][4].Suit().equals(cardOne);
            a[1][i] = sortedCard[i][2].Suit().equals(cardTwo) && sortedCard[i][3].Suit().equals(cardTwo) && sortedCard[i][4].Suit().equals(cardTwo) && sortedCard[i][5].Suit().equals(cardTwo);
            a[2][i] = sortedCard[i][3].Suit().equals(cardThree) && sortedCard[i][4].Suit().equals(cardThree) && sortedCard[i][5].Suit().equals(cardThree) && sortedCard[i][6].Suit().equals(cardThree);
            if(sortedCard[i][6].Suit().equals(cardOne)){
                a[3][i] = cardTwo.equals(cardOne) && cardThree.equals(cardOne) && sortedCard[i][3].Suit().equals(cardOne);
            }
        }
        for(int j = 0; j < a[0].length; j++){
            if((a[0][j] && flush[j]) || (a[1][j] && flush[j]) || (a[2][j] && flush[j]) || (a[3][j] && flush[j])){
                a[4][j] = true;
            }
        }
        return a[4];
    }

//    this method just determines if a hand just has matching suits
    private static boolean[] detectFlush(){
        Card[][] sortedCard = Sort();
        int[][] c = new int[Constants.numOfPlayers][4];
        boolean[] flush = new boolean[Constants.numOfPlayers];
        String[] suits = {"♥", "♣", "♦", "♠"};
        for(int a = 0; a < c.length; a++){
            for(int b = 0; b < 7; b++){
                if(sortedCard[a][b].Suit().equals(suits[0])){
                    c[a][0]++;
                }else if(sortedCard[a][b].Suit().equals(suits[1])){
                    c[a][1]++;
                }else if(sortedCard[a][b].Suit().equals(suits[2])){
                    c[a][2]++;
                }else if(sortedCard[a][b].Suit().equals(suits[3])){
                    c[a][3]++;
                }
            }
        }
        for(int d = 0; d < flush.length; d++)
            if(c[d][0] >= 5 || c[d][1] >= 5 || c[d][2] >= 5 || c[d][3] >= 5)
                flush[d] = true;
        return flush;
    }

//    this method determines what card is the highest value in the flush
    private static int[] highestFlush(){
        boolean[][] a = giveHighFlush();
        Card[][] sortedCard = Sort();
        int[] highCard = new int[Constants.numOfPlayers];
        for(int i = 0; i < highCard.length; i++){
            if(a[0][i]){
                highCard[i] = sortedCard[i][4].Value();
            }else if(a[1][i]){
                highCard[i] = sortedCard[i][5].Value();
            }else if(a[2][i]){
                highCard[i] = sortedCard[i][6].Value();
            }else if(a[3][i]){
                highCard[i] = 5;
            }else{
                highCard[i] = 0;
            }
        }
        return highCard;
    }

//    determines if a player has a royal flush and is just a simpler way of doing so
    private static boolean[] detectRoyal(){
        boolean[] royalFlush = new boolean[Constants.numOfPlayers];
        Card[][] sortedCard = Sort();
        for(int i = 0; i < Constants.numOfPlayers; i++){
            if(sortedCard[i][2].Value() == 10 && sortedCard[i][3].Value() == 11 && sortedCard[i][4].Value() == 12 && sortedCard[i][5].Value() == 13 && sortedCard[i][6].Value() == 14){
                royalFlush[i] = true;
            }
        }
        return royalFlush;
    }

//    passes in the raw boolean value to highest flush to determine what section has the highest card in the flush
    private static boolean[][] giveHighFlush(){
        Card[][] sortedCard = Sort();
        boolean[][] a = new boolean[4][Constants.numOfPlayers];
        for(int i = 0; i < a[0].length; i++){
            String cardOne = sortedCard[i][0].Suit();
            String cardTwo = sortedCard[i][1].Suit();
            String cardThree = sortedCard[i][2].Suit();
            a[0][i] = sortedCard[i][1].Suit().equals(cardOne) && sortedCard[i][2].Suit().equals(cardOne) && sortedCard[i][3].Suit().equals(cardOne) && sortedCard[i][4].Suit().equals(cardOne);
            a[1][i] = sortedCard[i][2].Suit().equals(cardTwo) && sortedCard[i][3].Suit().equals(cardTwo) && sortedCard[i][4].Suit().equals(cardTwo) && sortedCard[i][5].Suit().equals(cardTwo);
            a[2][i] = sortedCard[i][3].Suit().equals(cardThree) && sortedCard[i][4].Suit().equals(cardThree) && sortedCard[i][5].Suit().equals(cardThree) && sortedCard[i][6].Suit().equals(cardThree);
            if(sortedCard[i][6].Suit().equals(cardOne)){
                a[3][i] = cardTwo.equals(cardOne) && cardThree.equals(cardOne) && sortedCard[i][3].Suit().equals(cardOne);
            }
        }
        return a;
    }

//    determines what is the highest card that the player has
//    this is needed to determine which player gets the pot when they have the same cards
    private static int[] highestCard(){
        int[] hiCard = new int[Constants.numOfPlayers];
        Card[][] player = new Card[Constants.numOfPlayers][2];
        for(int j = 0; j < Constants.numOfPlayers; j++){
            player[j] = Deck.playerCards(j + 1);
        }
        for(int i = 0; i < hiCard.length; i++){
            hiCard[i] = player[i][1].Value();
            if(player[i][0].Value() > player[i][1].Value())
                hiCard[i] = player[i][0].Value();
        }
        return hiCard;
    }

//    determines if a player has a pair, two pair, three or four of a kind
    private static int[][] findSets(){
        Card[] arr = Deck.giveJustTable();
        Card[][] player = new Card[Constants.numOfPlayers][2];
        int[][] restHouse = new int[Constants.numOfPlayers][4];
        for(int j = 0; j < Constants.numOfPlayers; j++){
            player[j] = Deck.playerCards(j + 1);
        }
        ArrayList<Integer>[] nums = new ArrayList[Constants.numOfPlayers];
        for (int i = 0; i < Constants.numOfPlayers; i++) {
            nums[i] = new ArrayList<>();
        }

        for(int i = 0; i < Constants.numOfPlayers; i++){
            for (Card anArr : arr) {
                if (player[i][0].Value() == anArr.Value()) {
                    nums[i].add(anArr.Value());
                }
                if (player[i][1].Value() == anArr.Value()) {
                    nums[i].add(anArr.Value());
                }
            }
            if(player[i][0].Value() == player[i][1].Value()){
                nums[i].add(player[i][0].Value());
            }
        }
        for(int i = 0; i < Constants.numOfPlayers; i++){
            if(nums[i].size() == 1){
                restHouse[i][0] = nums[i].get(0);
            }
            if(nums[i].size() == 2){
                if(nums[i].get(0).equals(nums[i].get(1))){
                    restHouse[i][2] = nums[i].get(0);
                }else{
                    restHouse[i][0] = nums[i].get(0);
                    restHouse[i][1] = nums[i].get(1);
                }
            }
            if(nums[i].size() == 3){
                if(nums[i].get(0).equals(nums[i].get(1))){
                    restHouse[i][2] = nums[i].get(0);
                    restHouse[i][0] = nums[i].get(2);
                }else if(nums[i].get(0).equals(nums[i].get(2))){
                    restHouse[i][2] = nums[i].get(0);
                    restHouse[i][0] = nums[i].get(1);
                }else if(nums[i].get(1).equals(nums[i].get(2))){
                    restHouse[i][2] = nums[i].get(1);
                    restHouse[i][0] = nums[i].get(0);
                }else{
                    restHouse[i][3] = nums[i].get(0);
                }
            }
        }
        return restHouse;
    }

//    determines how many points each player gets
    private static double[] points(){
        boolean[] straight = detectStraight();
        boolean[] flush = detectFlush();
        boolean[] straightFlush = detectStraightFlush();
        boolean[] royal = detectRoyal();
        int[] highestFlush = highestFlush();
        int[] highestCard = highestCard();
        int[][] restOfHands = findSets();
        double[] points = new double[Constants.numOfPlayers];
        for(int i = 0; i < points.length; i++){
            if(straightFlush[i] && royal[i]){
                points[i] = 2140000000;
            }else if(straightFlush[i]){
                points[i] = (highestFlush[i] * 7000000) + (((double)highestCard[i]) / 100);
            }else if(restOfHands[i][3] > 0){
                points[i] = (restOfHands[i][3] * 600000) + (((double)highestCard[i]) / 100);
            }else if(restOfHands[i][2] > 0 && restOfHands[i][1] > 0){
                points[i] = ((restOfHands[i][2] + restOfHands[i][1]) * 50000) + (((double)highestCard[i]) / 100);
            }else if(flush[i]){
                points[i] = (highestFlush[i] * 4000) + (((double)highestCard[i]) / 100);
            }else if(straight[i]){
                points[i] = (highestFlush[i] * 300) + (((double)highestCard[i]) / 100);
            }else if(restOfHands[i][2] > 0){
                points[i] = (restOfHands[i][2] * 20) + (((double)highestCard[i]) / 100);
            }else if(restOfHands[i][1] > 0){
                points[i] = restOfHands[i][1] + (((double)highestCard[i]) / 100);
            }else if(restOfHands[i][0] > 0){
                points[i] = ((double) restOfHands[i][0]) / 10 + (((double)highestCard[i]) / 100);
            }else{
                points[i] = ((double)highestCard[i]) / 100;
            }
        }
        return points;
    }

//    determines which player has the most points and returns the player number
    static int winner(){
        double[] playerPoints = points();
        double mostPoints = 0;
        for(int i = 0; i < Constants.numOfPlayers; i++){
            if(playerPoints[i] > mostPoints){
                mostPoints = playerPoints[i];
            }
        }
        for(int j = 0; j < Constants.numOfPlayers; j++){
            if(playerPoints[j] == mostPoints){
                return j + 1;
            }
        }
        return 0;
    }

//    this method takes some methods found in deck that pass the player and table
//    and sorts each player and then feeds the 2D Card array to the check logic
    private static Card[][] Sort(){
        Card[][] arr = new Card[Constants.numOfPlayers][7];
        Card[] table = Deck.giveJustTable();
        for(int i = 0; i < Constants.numOfPlayers; i++){
            Card[] player = Deck.playerCards(i + 1);
            for(int j = 0; j < (player.length + table.length); j++){
                if(j < player.length)
                    arr[i][j] = player[j];
                else
                    arr[i][j] = table[j - 2];
            }
        }

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
