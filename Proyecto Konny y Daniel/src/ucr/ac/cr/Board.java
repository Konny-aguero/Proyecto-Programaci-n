package ucr.ac.cr;

public class Board {
    static final String EMPTY = "[ ]";
    private static final String WIZARD = "M";
    private static final String WARRIOR = "G";
    private static final String ARCHER = "A";
    private static final String TANK = "T";
    private static final String KILLER = "S";

//    public static final String COLOR_RED = "\u001B[31m";
//    public static final String COLOR_BLUE = "\u001B[34m";
//    public static final String COLOR_RESET = "\u001B[0m";
//
//    private static final String PLAYER_1_COLOR = COLOR_RED;
//    private static final String PLAYER_2_COLOR = COLOR_BLUE;
//
//    public static void printColor(String texto, String color) {
//        System.out.println(color + texto + COLOR_RESET);
//    }

    static String[][] board;
    static void initBoard(int size) {
        board = new String[size][size];
        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board.length; column++) {
                board[row][column] = EMPTY;
            }
        }
    }

    static void showBoard(int size){
        for (int i = 1; i<=size; i++){
            System.out.print("\t"+i);
        }
        System.out.println();
        for (int row = 0; row < board.length; row++) {
            System.out.print(row+1+"\t");
            for (int column = 0; column < board.length; column++) {
                System.out.print(board[row][column]);
                System.out.print("\t");
            }
            System.out.println();
        }
    }

    static void automaticHeroesInitialization(String[][] board, int size, Hero[] hero1, Hero[] hero2){
        board[0][0] = "["+hero1[0].getSymbol()+"]"; board[0][1] = "["+hero1[1].getSymbol()+"]"; board[0][2] = "["+hero1[2].getSymbol()+"]"; board[0][3] = "["+hero1[3].getSymbol()+"]"; board[0][4] = "["+hero1[4].getSymbol()+"]";

        board[size-1][0] = "["+hero2[0].getSymbol()+"]"; board[size-1][1] = "["+hero2[1].getSymbol()+"]"; board[size-1][2] = "["+hero2[2].getSymbol()+"]"; board[size-1][3] = "["+hero2[3].getSymbol()+"]"; board[size-1][4] = "["+hero2[4].getSymbol()+"]";
    }
}