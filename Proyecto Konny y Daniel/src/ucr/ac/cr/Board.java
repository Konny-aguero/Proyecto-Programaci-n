package ucr.ac.cr;

import java.util.Scanner;

public class Board {
    static final String EMPTY = "[ ]";
//    public static final String COLOR_RED = "\u001B[31m";
//    private static final String PLAYER_1_COLOR = COLOR_RED;
//    private static final String PLAYER_2_COLOR = COLOR_BLUE;
//    public static void printColor(String texto, String color) {
//        System.out.println(color + texto + COLOR_RESET);
//    }

    public static final String COLOR_BLUE = "\u001B[34m";
    public static final String COLOR_RESET = "\u001B[0m";

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
        for (int i = 1; i <= size; i++){
            System.out.print("\t" + i);
        }
        System.out.println();
        for (int row = 0; row < board.length; row++) {
            System.out.print(row + 1 + "\t");
            for (int column = 0; column < board.length; column++) {
                String cell = board[row][column];
                if (!cell.equals(EMPTY)) {
                    System.out.print(COLOR_BLUE + cell + COLOR_RESET);
                } else {
                    System.out.print(cell);
                }
                System.out.print("\t");
            }
            System.out.println();
        }
    }

    static void automaticHeroesInitialization(String[][] board, int size, Hero[] hero1, Hero[] hero2){
        board[0][0] = "["+hero1[0].getSymbol()+"]"; board[0][1] = "["+hero1[1].getSymbol()+"]"; board[0][2] = "["+hero1[2].getSymbol()+"]"; board[0][3] = "["+hero1[3].getSymbol()+"]"; board[0][4] = "["+hero1[4].getSymbol()+"]";

        board[size-1][0] = "["+hero2[0].getSymbol()+"]"; board[size-1][1] = "["+hero2[1].getSymbol()+"]"; board[size-1][2] = "["+hero2[2].getSymbol()+"]"; board[size-1][3] = "["+hero2[3].getSymbol()+"]"; board[size-1][4] = "["+hero2[4].getSymbol()+"]";
    }

    static void colocationHeroes(Hero[] hero1, Hero[] hero2, String[][] board, Scanner sc) {
        for (int h = 0; h < hero1.length; h++) {
            System.out.println("Turno del Jugador 1");
            showAvailableHeroes(hero1);
            placeHero(hero1, board, sc, 1);
            showBoard(board.length);
        }

        for (int h = 0; h < hero2.length; h++) {
            System.out.println("Turno del Jugador 2");
            showAvailableHeroes(hero2);
            placeHero(hero2, board, sc, 2);
            showBoard(board.length);
        }

        System.out.println();
        System.out.println("Tablero final:");
        showBoard(board.length);
    }

    static void showAvailableHeroes(Hero[] heroes) {
        System.out.println("Lista disponible:");
        for (int i = 0; i < heroes.length; i++) {
            if (heroes[i] != null) {
            System.out.println((i + 1) + ". " + heroes[i].getSymbol());
            }
        }
    }

    static void placeHero(Hero[] heroes, String[][] board, Scanner sc, int player) {
        int option = 1;
        boolean validOption = false;

        while (!validOption) {
            System.out.print("Héroe: ");
            option = sc.nextInt() - 1; // -1 para ajustar al índice del array
            if (option < 0 || option >= heroes.length || heroes[option] == null) {
                System.out.println("Opción inválida.");
            } else {
                validOption = true;
            }
        }


        int size = board.length;
        int row = 1, col = 1;
        boolean validOption2 = false;

        while (!validOption2) {
            System.out.print("Fila: ");
            row = sc.nextInt() - 1;
            System.out.print("Columna: ");
            col = sc.nextInt() - 1;

            if (player == 1 && (row < 0 || row > 1)) {
                System.out.println("Solo puedes colocar en las filas 1 y 2");
            } else if (player == 2 && (row < size - 2 || row > size - 1)) {
                System.out.println("Solo puedes colocar en las filas " + size + " y " + (size - 1));
            } else if (col < 0 || col >= size || !board[row][col].equals(EMPTY)) {
                System.out.println("Posición inválida");
            } else {
                validOption2 = true;
            }
        }

        board[row][col] = "[" + heroes[option].getSymbol() + "]";
        heroes[option] = null;
    }

}