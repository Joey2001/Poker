class CardMaker{

    static String[][] constructCard(){

        String topBot = " ----- ";

        String[] one = {"|  ♥  |", "|  ♣  |", "|  ♦  |", "|  ♠  |"};

        String[] newR = new String[13];
        String[] newL = new String[13];

        for(int i = 0; i < 10; i++){
            if(i + 1 < 10){
                newR[i] = "|" + (i + 1) + "    |";
                newL[i] = "|    " + (i + 1) + "|";
            }else{
                newR[i] = "|" + (i + 1) + "   |";
                newL[i] = "|   " + (i + 1) + "|";
            }
        }
        newR[10] = "|J    |";
        newL[10] = "|    J|";
        newR[11] = "|Q    |";
        newL[11] = "|    Q|";
        newR[12] = "|K    |";
        newL[12] = "|    K|";

        String[][] parts = {one, newR, newL};
        String[][] e = new String[52][5];

        int count = -1;
        int falseLoop;
        for(int i = 0; i < e.length; i++){
            if(i % 13 == 0){
                count++;
            }
            falseLoop = (i - (count * 13));

            for(int j = 0; j < e[i].length; j++){

                if(j == 0 || j == 4){
                    e[i][j] = topBot;
                }else if(j == 2){
                    e[i][j] = parts[0][count];
                }else if(j == 3){
                    e[i][j] = parts[2][falseLoop];
                }else{
                    e[i][j] = parts[1][falseLoop];
                }
            }
        }
        return e;
    }

    static String[] symbol(){
        String[] symbol = new String[52];
        int count = -1;
        String[] one = {"|  ♥  |", "|  ♣  |", "|  ♦  |", "|  ♠  |"};
        for(int i = 0; i < symbol.length; i++){
            if(i % 13 == 0){
                count++;
            }
            symbol[i] = one[count];
        }
        return symbol;
    }

    static int[] value() {
        int[] val = new int[52];
        for(int i = 0; i < val.length; i++){
            val[i] = i + 1;
        }
        return val;
    }

    static void printCard(String[][] card){
        for(int i = 0; i < card[0].length; i++){
            for (String[] aCard : card)
                System.out.print(aCard[i] + "      ");
            System.out.println();
        }
    }
}