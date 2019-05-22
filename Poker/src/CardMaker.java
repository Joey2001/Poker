class CardMaker{

    static String[][] constructCard(){

//        initializing and creating Strings and String arrays to fill the 2D string array
//        used to pass into the deck class
        String topBot = " ----- ";
        String[] one = {"|  ♥  |", "|  ♣  |", "|  ♦  |", "|  ♠  |"};
        String[] JQK = {"J", "Q", "K"};

        String[] newR = new String[13];
        String[] newL = new String[13];

        newR[0]  = "|A    |";
        newL[0]  = "|    A|";

        for(int i = 1; i < 10; i++){
            if(i != 9){
                newR[i] = "|" + (i + 1) + "    |";
                newL[i] = "|    " + (i + 1) + "|";
            }else{
                newR[i] = "|" + (i + 1) + "   |";
                newL[i] = "|   " + (i + 1) + "|";
            }
        }

        for(int i = 10; i < newR.length; i++){
            newR[i] = "|" + JQK[i - 10] + "    |";
            newL[i] = "|    " + JQK[i - 10] + "|";
        }

        String[][] parts = {one, newR, newL};
        String[][] e = new String[52][5];

//        fills the 2D array with the proper String arrays
        int count = -1;
        for(int i = 0; i < e.length; i++){
            if(i % 4 == 0)
                count++;
            for(int j = 0; j < e[i].length; j++){

                if(j == 0 || j == 4){
                    e[i][j] = topBot;
                }else if(j == 2){
                    e[i][j] = parts[0][i % 4];
                }else if(j == 3){
                    e[i][j] = parts[2][count];
                }else{
                    e[i][j] = parts[1][count];
                }
            }
        }
        return e;
    }

    static void printCard(String[][] card){
//        prints each section of each card in such a way that the
//        result is all of the card being printed from left to right
        for(int i = 0; i < card[0].length; i++){
            for (String[] aCard : card)
                System.out.print(aCard[i] + "      ");
            System.out.println();
        }
    }
}
