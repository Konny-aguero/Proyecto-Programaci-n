package ucr.ac.cr;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Archer extends Hero {

    public Archer(int hp, int row, int col, char symbol, int damageDealt, int damageTaken, int kills, int moves, boolean placed) {
        super(hp, row, col, symbol, damageDealt, damageTaken, kills, moves, placed);
    }

    public Archer() {
    }

    public Archer(int hp, char symbol) {
        super(hp, symbol);
    }

    @Override
    public boolean move(int newRow, int newCol, int boardSize) {
        if (Math.abs(newRow - getRow()) <= 1 && Math.abs(newCol - getCol()) <= 1) {
            if (isInsideBoard(newRow, newCol, boardSize)) {

                if (!Board.board[newRow][newCol].equals("[ ]")) {
                    System.out.println("❌ Movimiento inválido: la casilla (" + (newRow+1) + "," + (newCol+1) + ") está ocupada por otro héroe.");
                    return false;
                }
                setRow(newRow);
                setCol(newCol);
                addMove();
                System.out.println("🏹 Arquero se movió a (" + (newRow + 1)+ "," + (newCol + 1)+")");
                return true;
            }
        }
        System.out.println("Movimiento inválido para el Arquero!, intente nuevamente...");
        return false;
    }

    @Override
    public boolean attack(Hero target) {
        int rowDiff = target.getRow() - getRow();
        int colDiff = target.getCol() - getCol();

        boolean enLinea = (rowDiff == 0 || colDiff == 0 || Math.abs(rowDiff) == Math.abs(colDiff));
        int distancia = Math.max(Math.abs(rowDiff), Math.abs(colDiff));

        if (enLinea && distancia >= 1 && distancia <= 3) {
            int damage = 5 + (int)(Math.random() * 3);

            if (target instanceof Tank) damage += 2;
            if (target instanceof Warrior) damage -= 1;
            if (target instanceof Wizard) damage -= 1;

            if (damage < 0) damage = 0;
            target.takeDamage(damage);
            addDamageDealt(damage);
            if (!target.isAlive()) addKill();
            System.out.println("🏹 Arquero dispara y hace " + damage + " de daño a " + target.getSymbol() + "!");
            return true;
        } else {
            System.out.println("❌ Objetivo fuera de alcance del arquero.");
            return false;
        }
    }
}
