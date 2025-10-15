package ucr.ac.cr;
import java.util.Random;
public class Warrior extends Hero {
    public Warrior(int hp, char symbol) {
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
                System.out.println("⚔️ Guerrero se movió a (" + (newRow+1) + "," + (newCol+1) + ")");
                return true;
            }
        }

        System.out.println("Movimiento inválido para el Guerrero!, intente nuevamente...");
        return false;
    }

    @Override
    public boolean attack(Hero target) {
        int rowDifference = Math.abs(target.getRow() - getRow());
        int colDifference = Math.abs(target.getCol() - getCol());

        if ((rowDifference == 1 && colDifference == 0) || (rowDifference == 0 && colDifference == 1)) {
            Random rand = new Random();
            int damage = 6 + rand.nextInt(3);

            // Si el enemigo es Mago, recibir más daño
            if (target instanceof Wizard) {
                damage += 2;  // bonificación por debilidad
            }

            target.takeDamage(damage);
            addDamageDealt(damage);

            if (!target.isAlive()) addKill();

            System.out.println("⚔️ Guerrero ataca y hace " + damage + " de daño!");
            return true;
        } else {
            System.out.println("Objetivo fuera de rango para el Guerrero!");
            return false;
        }
    }

    @Override
    public int[][] validMoves(int boardSize) {
        int[][] moves = new int[4][2];
        int count = 0;
        int r = getRow();
        int c = getCol();

        if (r > 0 && Board.isEmpty(r - 1, c))              // arriba
            moves[count++] = new int[]{r - 1, c};

        if (r < boardSize - 1 && Board.isEmpty(r + 1, c))  // abajo
            moves[count++] = new int[]{r + 1, c};

        if (c > 0 && Board.isEmpty(r, c - 1))              // izquierda
            moves[count++] = new int[]{r, c - 1};

        if (c < boardSize - 1 && Board.isEmpty(r, c + 1))  // derecha
            moves[count++] = new int[]{r, c + 1};

        int[][] result = new int[count][2];
        for (int i = 0; i < count; i++) {
            result[i] = moves[i];
        }

        return result;
    }
}
