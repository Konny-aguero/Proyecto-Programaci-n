package ucr.ac.cr;
import java.util.Random;

public class Wizard extends Hero {

    public Wizard(int hp, char symbol) {
        super(hp, symbol);
    }

    @Override
    public boolean move(int newRow, int newCol, int boardSize) {
        if (Math.abs(newRow - getRow()) <= 2 && Math.abs(newCol - getCol()) <= 2) {
            if (isInsideBoard(newRow, newCol, boardSize)) {
                setRow(newRow);
                setCol(newCol);
                addMove();
                System.out.println("🪄 Mago se movió a (" + (newRow + 1) + "," + (newCol + 1) + ")");
                return true;
            }
        }
        System.out.println("Movimiento inválido para el Mago!, intente nuevamente...");
        return false;
    }

    @Override
    public boolean attack(Hero target) {
        int rowDiff = target.getRow() - getRow();
        int colDiff = target.getCol() - getCol();
        if (Math.abs(rowDiff) <= 1 && Math.abs(colDiff) <= 1) {
            Random rand = new Random();
            int damage = 4 + rand.nextInt(4);

            if (target instanceof Warrior) damage += 2;

            target.takeDamage(damage);
            addDamageDealt(damage);
            if (!target.isAlive()) addKill();
            System.out.println("🪄 Mago lanza hechizo e inflige " + damage + " de daño a " + target.getSymbol() + "!");
            return true;
        } else {
            System.out.println("❌ Objetivo fuera de alcance del Mago.");
            return false;
        }
    }
    @Override
    public int[][] validMoves(int boardSize) {
        int[][] moves = new int[24][2]; // máximo 24 posiciones
        int count = 0;
        for (int r = getRow() - 2; r <= getRow() + 2; r++) {
            for (int c = getCol() - 2; c <= getCol() + 2; c++) {
                if (r == getRow() && c == getCol()) continue;
                if (r >= 0 && r < boardSize && c >= 0 && c < boardSize) {
                    moves[count][0] = r;
                    moves[count][1] = c;
                    count++;
                }
            }
        }
        int[][] result = new int[count][2];
        for (int i = 0; i < count; i++) result[i] = moves[i];
        return result;
    }

}
