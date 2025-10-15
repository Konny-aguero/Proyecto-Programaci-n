package ucr.ac.cr;
import java.util.Scanner;
import static ucr.ac.cr.GameConfig.*;
public class Board {
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
        // Jugador 1 (fila 0)
        for (int i = 0; i < hero1.length; i++) {
            board[0][i] = "[" + hero1[i].getSymbol() + "]";
            hero1[i].setRow(0);
            hero1[i].setCol(i);
        }
        // Jugador 2 (última fila)
        for (int i = 0; i < hero2.length; i++) {
            board[size-1][i] = "[" + hero2[i].getSymbol() + "]";
            hero2[i].setRow(size-1);
            hero2[i].setCol(i);
        }
    }

    static void PlaceHeroesManually(Hero[] hero1, Hero[] hero2, String[][] board, Scanner sc) {
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
            try {
                System.out.print("Héroe: ");
                option = sc.nextInt() - 1; // -1 para ajustar al índice del array
                if (option < 0 || option >= heroes.length || heroes[option].isPlaced()) {
                    System.out.println("Opción inválida, el héroe ya fue colocado o no existe. Intenta de nuevo.");
                } else {
                    validOption = true;
                }
            } catch (Exception e) {
                System.out.println("Entrada inválida. Por favor, ingresa un número.");
                sc.next();
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
        // Actualiza la posición del héroe
        heroes[option].setRow(row);
        heroes[option].setCol(col);
        heroes[option].setPlaced(true);


    }

    public static boolean isEmpty(int r, int c) {
        return board[r][c].equals(EMPTY);
    }
}