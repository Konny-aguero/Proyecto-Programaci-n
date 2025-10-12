//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ucr.ac.cr;

import java.util.Random;

public class Tank extends Hero {
    public Tank(int hp, char symbol) {
        super(hp, symbol);
    }


    @Override
    public boolean move(int newRow, int newCol, int boardSize) {

        int rowdifference = Math.abs(newRow - getRow());
        int columndifference = Math.abs(newCol - getCol());

        if (rowdifference == 1 && columndifference == 0 || columndifference == 1 && rowdifference == 0) {
            if (isInsideBoard(newRow, newCol, boardSize)) {
                setRow(newRow);
                setCol(newCol);
                addMove();
                System.out.println("🛡️ Tanque se movió a (" + newRow + "," + newCol + ")");
                return true;
            }
        }

        System.out.println("Movimiento inválido para el Tanque!, intente nuevamente...");
        return false;
    }

    @Override
    public boolean attack(Hero target) {
        int rowDifference = Math.abs(target.getRow() - getRow());
        int colDifference = Math.abs(target.getCol() - getCol());

        if ((rowDifference == 1 && colDifference == 0) || (rowDifference == 0 && colDifference == 1)) {
            Random rand = new Random();
            int damage = 3 + rand.nextInt(2);

            target.takeDamage(damage);
            addDamageDealt(damage);

            if (!target.isAlive()) addKill();

            System.out.println("🛡️ Tanque ataca y hace " + damage + " de daño!");
            return true;
        } else {
            System.out.println("Objetivo fuera de rango para el Tanque!");
            return false;
        }
    }

    @Override
    public void takeDamage(int damage) {
        int reduced = damage - 1;
        if (reduced < 0) reduced = 0;
        super.takeDamage(reduced);
    }
    @Override
    public int[][] validMoves(int boardSize) {
        int[][] moves = new int[4][2];
        int count = 0;
        int r = getRow();
        int c = getCol();

        // arriba
        if (r > 0 && Board.isEmpty(r - 1, c))
            moves[count++] = new int[]{r - 1, c};

        // abajo
        if (r < boardSize - 1 && Board.isEmpty(r + 1, c))
            moves[count++] = new int[]{r + 1, c};

        // izquierda
        if (c > 0 && Board.isEmpty(r, c - 1))
            moves[count++] = new int[]{r, c - 1};

        // derecha
        if (c < boardSize - 1 && Board.isEmpty(r, c + 1))
            moves[count++] = new int[]{r, c + 1};

        int[][] result = new int[count][2];
        for (int i = 0; i < count; i++) result[i] = moves[i];
        return result;
    }


}
