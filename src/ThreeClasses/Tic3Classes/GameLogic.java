package ThreeClasses.Tic3Classes;

import java.util.Scanner;

public class GameLogic {
    public String ActiveGamer;
    public final String KRESTIK = " X ",NOLIK = " 0 ",EMPTINESS = "   ";


    public int StatusGame;
    public final int Status_Game_isInProgress = 0,Status_Game_Draw = 1,Status_Game_Winner_X = 3,Status_Game_Winner_O = 4;
    public Scanner InPut = new Scanner(System.in);

    Field field = new Field();
    String[][] C = field.CELL;

    public void FindOutWinner(){
        StartGame();
        do {
            GetConditionGame();
            AnalyzeStatus();
            field.ShowCell();
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
    public void StartGame(){
        for (int line = 0;line<field.LINES;line++){
            for (int column = 0;column<field.COLUMNS;column++){
                field.CELL[line][column] = EMPTINESS;
            }
        }
        ActiveGamer = KRESTIK;
        field.ShowCell();

    }

    public void GetConditionGame(){
        boolean InPutisValid = false;
        do {
            System.out.println("Игрок " + ActiveGamer + ", введите строку от 1-3 и столбик от 1-3 через пробел ");
            int line = InPut.nextInt()-1;
            int column = InPut.nextInt()-1;
            if (line>=0  && line <field.LINES  && column>=0 && column < field.COLUMNS  && field.CELL[line][column]==EMPTINESS){
                field.CELL[line][column] = ActiveGamer;
                InPutisValid = true;
            }else {
                System.out.println("Выбранное размещение (" + (line + 1) + "," + (column + 1) + ") не может использоваться.Попробуйте снова....");
            }
        }
        while ( InPutisValid != true);
    }
    public String AnalyzeCell(){
        int theSameItems;

        for (int line=0;line<field.LINES;line++){
            theSameItems = 0;
            for (int column=0;column<field.COLUMNS;column++){
                if (field.CELL[line][0] != EMPTINESS   && field.CELL[line][0] == field.CELL[line][column]){
                    theSameItems++;
                }
            }
            if (theSameItems == 3){
                return field.CELL[line][0];
            }
        }
        for (int column=0;column<field.COLUMNS;column++){
            theSameItems = 0;
            for (int line=0;line<field.LINES;line++){
                if (field.CELL[0][column] != EMPTINESS   && field.CELL[0][column] == field.CELL[line][column]){
                    theSameItems++;
                }
            }
            if (theSameItems == 3){
                return field.CELL[0][column];
            }
        }
        if (field.CELL[0][0] != EMPTINESS   && field.CELL[0][0] == field.CELL[1][1]   && field.CELL[1][1] == field.CELL[2][2]){
            return field.CELL[0][0];
        }
        if (field.CELL[0][2] != EMPTINESS   && field.CELL[1][1] == field.CELL[0][2]   && field.CELL[2][0] == field.CELL[0][2]){
            return field.CELL[0][2];
        }
        return EMPTINESS;
    }
    public boolean CheckCellsEmpty(){
        for (int line=0;line<field.LINES;line++){
            for (int column=0;column<field.COLUMNS;column++){
                if (field.CELL[line][column] ==EMPTINESS){
                    return false;
                }
            }
        }
        return true;
    }
    public void AnalyzeStatus(){
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
