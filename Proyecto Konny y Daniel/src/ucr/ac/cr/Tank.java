package ucr.ac.cr;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Tank extends Hero {
    public Tank(int hp, char symbol) {
        super(hp, symbol);
    }

    public Tank(int hp, int row, int col, char symbol, int damageDealt, int damageTaken, int kills, int moves, boolean placed) {
        super(hp, row, col, symbol, damageDealt, damageTaken, kills, moves, placed);
    }

    public Tank() {
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
            int damage = 3 + (int)(Math.random() * 2);

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
}
