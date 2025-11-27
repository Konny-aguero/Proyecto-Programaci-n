package ucr.ac.cr;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Warrior extends Hero {
    public Warrior(int hp, char symbol) {
        super(hp, symbol);
    }

    public Warrior(int hp, int row, int col, char symbol, int damageDealt, int damageTaken, int kills, int moves, boolean placed) {
        super(hp, row, col, symbol, damageDealt, damageTaken, kills, moves, placed);
    }

    public Warrior() {
    }

    @Override
    public boolean move(int newRow, int newCol, int boardSize) {
        int rowdifference = Math.abs(newRow - getRow());
        int columndifference = Math.abs(newCol - getCol());

        if (rowdifference == 1 && columndifference == 0 || columndifference == 1 && rowdifference == 0) {
            if (isInsideBoard(newRow, newCol, boardSize)) {
                if (!Board.board[newRow][newCol].equals("[ ]")) {
                    System.out.println("❌ Movimiento inválido: la casilla (" + (newRow+1) + "," + (newCol+1) + ") está ocupada por otro héroe.");
                    return false;
                }
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
            int damage = 6 + (int)(Math.random() * 3);
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
}
