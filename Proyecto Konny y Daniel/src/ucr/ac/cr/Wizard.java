package ucr.ac.cr;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Wizard extends Hero {
    public Wizard(int hp, char symbol) {
        super(hp, symbol);
    }

    public Wizard(int hp, int row, int col, char symbol, int damageDealt, int damageTaken, int kills, int moves, boolean placed) {
        super(hp, row, col, symbol, damageDealt, damageTaken, kills, moves, placed);
    }

    public Wizard() {
    }

    @Override
    public boolean move(int newRow, int newCol, int boardSize) {
        if (Math.abs(newRow - getRow()) <= 2 && Math.abs(newCol - getCol()) <= 2) {
            if (isInsideBoard(newRow, newCol, boardSize)) {
                if (!Board.board[newRow][newCol].equals("[ ]")) {
                    System.out.println("❌ Movimiento inválido: la casilla (" + (newRow+1) + "," + (newCol+1) + ") está ocupada por otro héroe.");
                    return false;
                }
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
            int damage = 4 + (int)(Math.random() * 4);


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

    public boolean attackArea(Hero[] enemies) {
        boolean atacado = false;

        for (Hero enemy : enemies) {
            if (enemy == null || !enemy.isAlive()) continue;

            int rowDiff = Math.abs(enemy.getRow() - getRow());
            int colDiff = Math.abs(enemy.getCol() - getCol());
            if (rowDiff <= 1 && colDiff <= 1) {
                int damage = 4 + (int)(Math.random() * 4); // 4-7
                if (enemy instanceof Warrior) damage += 2;
                enemy.takeDamage(damage);
                addDamageDealt(damage);
                if (!enemy.isAlive()) addKill();
                System.out.println("🪄 Mago lanza hechizo e inflige " + damage + " de daño a " + enemy.getSymbol() + "!");
                atacado = true;
            }
        }

        if (!atacado) {
            System.out.println("❌ No hay enemigos adyacentes para atacar.");
        }
        return atacado;
    }
}
