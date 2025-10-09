
package ucr.ac.cr;

import java.util.Random;

public class Killer extends Hero {


    public Killer(int hp, char symbol) {
        super(hp, symbol);
    }

    @Override
    public boolean move(int newRow, int newCol, int boardSize) {
        if (Math.abs(newRow - getRow()) == 2 && Math.abs(newCol - getCol()) == 2) {
            if (isInsideBoard(newRow, newCol, boardSize)) {
                setRow(newRow);
                setCol(newCol);
                addMove();
                System.out.println("🗡️ Asesino se movió a (" + newRow + "," + newCol + ")");
                return true;
            }
        }

        System.out.println("Movimiento inválido para el Asesino!, intente nuevamente...");
        return false;
    }

    @Override
    public boolean attack(Hero target) {
        Random rand = new Random();
        int totalDamage = 0;

        for (int i = 0; i < 2; i++) {
            int damage = 7 + rand.nextInt(3);

            if (target instanceof Archer || target instanceof Wizard) {
                damage += 3;
            }

            target.takeDamage(damage);
            addDamageDealt(damage);
            totalDamage += damage;

            if (!target.isAlive()) {
                addKill();
                System.out.println("🗡️ Asesino atacó a " + target.getSymbol() + " y lo derrotó con " + totalDamage + " de daño!");
                return true;
            }
        }

        System.out.println("🗡️ Asesino atacó a " + target.getSymbol() + " y le hizo " + totalDamage + " de daño. Sigue vivo.");
        return false;
    }
    @Override
    public int[][] validMoves(int boardSize) {
        int[][] moves = new int[4][2];
        int count = 0;
        int r = getRow();
        int c = getCol();
        if (r - 2 >= 0 && c - 2 >= 0) moves[count++] = new int[]{r-2, c-2}; // arriba-izquierda
        if (r - 2 >= 0 && c + 2 < boardSize) moves[count++] = new int[]{r-2, c+2}; // arriba-derecha
        if (r + 2 < boardSize && c - 2 >= 0) moves[count++] = new int[]{r+2, c-2}; // abajo-izquierda
        if (r + 2 < boardSize && c + 2 < boardSize) moves[count++] = new int[]{r+2, c+2}; // abajo-derecha
        int[][] result = new int[count][2];
        for (int i = 0; i < count; i++) result[i] = moves[i];
        return result;
    }

}
