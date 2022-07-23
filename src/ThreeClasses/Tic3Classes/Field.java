package ThreeClasses.Tic3Classes;

public class Field {
    public final int LINES = 3,COLUMNS = 3;
    public final String[][] CELL = new String[LINES][COLUMNS];
    public void ShowCell(){
        for (int line = 0;line < LINES;line++){
            for (int column = 0;column < COLUMNS;column++){
                System.out.print(CELL[line][column]);
                if (column != COLUMNS - 1) {
                    System.out.print(" |");
                }
            }
            System.out.println();

            if (line!=LINES-1){
                System.out.println("---------------");
            }
        }
        System.out.println();
    }
}