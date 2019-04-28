class CardMaker{

    static String[][] constructCard(){

        String topBot = " ----- ";

        String[] one = {"|  ♥  |", "|  ♣  |", "|  ♦  |", "|  ♠  |"};

        String[] newR = new String[13];
        String[] newL = new String[13];

        for(int i = 1; i < 10; i++){
            if(i + 1 < 10){
                newR[i] = "|" + (i + 1) + "    |";
                newL[i] = "|    " + (i + 1) + "|";
            }else{
                newR[i] = "|" + (i + 1) + "   |";
                newL[i] = "|   " + (i + 1) + "|";
            }
        }
        newR[0]  = "|A    |";
        newL[0]  = "|    A|";
        newR[10] = "|J    |";
        newL[10] = "|    J|";
        newR[11] = "|Q    |";
        newL[11] = "|    Q|";
        newR[12] = "|K    |";
        newL[12] = "|    K|";

        String[][] parts = {one, newR, newL};
        String[][] e = new String[52][5];

        int count = -1;
        for(int i = 0; i < e.length; i++){
            if(i % 4 == 0){
                count++;
            }

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
        for(int i = 0; i < card[0].length; i++){
            for (String[] aCard : card)
                System.out.print(aCard[i] + "      ");
            System.out.println();
        }
    }
}
