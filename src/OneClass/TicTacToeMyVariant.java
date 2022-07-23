package OneClass;

import java.util.Scanner;

public class TicTacToeMyVariant {
    public static final String EMPTINESS = "   ", KRESTIK = " X ", NOLIK = " O ";
    public static final int LINES = 3,COLUMNS = 3;
    public static final String[][] CELL = new String[LINES][COLUMNS];
    public static int StatusGame;
    public static final int Status_Game_isInProgress = 0,Status_Game_Draw = 1,Status_Game_Winner_X = 3,Status_Game_Winner_O = 4;
    public static String ActiveGamer;
    public static Scanner InPut = new Scanner(System.in);

    public static void main(String[] args) {
        StartGame();
        do {
            GetConditionGame();
            AnalyzeStatus();
            ShowCell();
            if (StatusGame == Status_Game_Winner_X){
                System.out.println("Выиграл 'X'");
            }else if (StatusGame == Status_Game_Winner_O){
                System.out.println("Выиграл '0'");
            }else if (StatusGame == Status_Game_Draw){
                System.out.println("Ничья");
            }
            ActiveGamer = (ActiveGamer == KRESTIK ? NOLIK:KRESTIK);

        }while (StatusGame == Status_Game_isInProgress);
    }

    public static void ShowCell(){
        for (int line = 0;line < LINES;line++){
            for (int column = 0;column < COLUMNS;column++){
                System.out.print(CELL[line][column]);
                if (column != COLUMNS - 1) {
                    System.out.print(" | ");
                }
            }
            System.out.println();

            if (line!=LINES-1){
                System.out.println("---------------");
            }
        }
        System.out.println();
    }
    public static void StartGame(){
        for (int line = 0;line<LINES;line++){
            for (int column = 0;column<COLUMNS;column++){
                CELL[line][column] = EMPTINESS;
            }
        }
        ActiveGamer = KRESTIK;
        ShowCell();

    }
    public static void GetConditionGame(){
        boolean InPutisValid = false;

        do {
            System.out.println("Игрок " + ActiveGamer + ", введите строку от 1-3 и столбик от 1-3 через пробел ");
            int line = InPut.nextInt()-1;
            int column = InPut.nextInt()-1;
            if (line>=0  && line < LINES  && column>=0 && column < COLUMNS  && CELL[line][column]==EMPTINESS){
                CELL[line][column] = ActiveGamer;
                InPutisValid = true;
            }else {
                System.out.println("Выбранное размещение (" + (line + 1) + "," + (column + 1) + ") не может использоваться.Попробуйте снова....");
            }
        }
        while ( InPutisValid != true);
    }
    public static String AnalyzeCell(){
        int theSameItems;

        for (int line=0;line<LINES;line++){
            theSameItems = 0;
            for (int column=0;column<COLUMNS;column++){
                if (CELL[line][0] != EMPTINESS   && CELL[line][0] == CELL[line][column]){
                    theSameItems++;
                }
            }
            if (theSameItems == 3){
                return CELL[line][0];
            }
        }
        for (int column=0;column<COLUMNS;column++){
            theSameItems = 0;
            for (int line=0;line<LINES;line++){
                if (CELL[0][column] != EMPTINESS   && CELL[0][column] == CELL[line][column]){
                    theSameItems++;
                }
            }
            if (theSameItems == 3){
                return CELL[0][column];
            }
        }
        if (CELL[0][0] != EMPTINESS   && CELL[0][0] == CELL[1][1]   && CELL[1][1] == CELL[2][2]){
            return CELL[0][0];
        }
        if (CELL[0][2] != EMPTINESS   && CELL[1][1] == CELL[0][2]   && CELL[2][0] == CELL[0][2]){
            return CELL[0][2];
        }
        return EMPTINESS;
    }
    public static boolean CheckCellsEmpty(){
        for (int line=0;line<LINES;line++){
            for (int column=0;column<COLUMNS;column++){
                if (CELL[line][column] ==EMPTINESS){
                    return false;
                }
            }
        }
        return true;
    }
    public static void AnalyzeStatus(){
        String Winner = AnalyzeCell();
        if (Winner.equals(KRESTIK)){
            StatusGame = Status_Game_Winner_X;
        }else if (Winner.equals(NOLIK)){
            StatusGame = Status_Game_Winner_O;
        }else if (CheckCellsEmpty()){
            StatusGame = Status_Game_Draw;
        }else {
            StatusGame = Status_Game_isInProgress;
        }
    }
}
