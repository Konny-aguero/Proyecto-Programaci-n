package ucr.ac.cr;

import java.util.Random;

public class Archer extends Hero {

    public Archer(int hp, char symbol) {
        super(hp, symbol);
    }

    @Override
    public boolean move(int newRow, int newCol, int boardSize) {
        if (Math.abs(newRow - getRow()) <= 1 && Math.abs(newCol - getCol()) <= 1) {
            if (isInsideBoard(newRow, newCol, boardSize)) {
                setRow(newRow);
                setCol(newCol);
                addMove();
                System.out.println("🏹 Arquero se movió a (" + newRow + "," + newCol + ")");
                return true;
            }
        }
        System.out.println("Movimiento inválido para el Arquero!");
        return false;
    }

    @Override
    public void attack(Hero target) {
        int rowDiff = target.getRow() - getRow();
        int colDiff = target.getCol() - getCol();

        boolean enLinea = (rowDiff == 0 || colDiff == 0 || Math.abs(rowDiff) == Math.abs(colDiff));
        int distancia = Math.max(Math.abs(rowDiff), Math.abs(colDiff));

        if (enLinea && distancia >= 1 && distancia <= 3) {
            Random rand = new Random();
            int damage = 5 + rand.nextInt(3);
            if (target instanceof Tank) damage += 2;
            target.takeDamage(damage);
            addDamageDealt(damage);
            if (!target.isAlive()) addKill();
            System.out.println("🏹 Arquero dispara y hace " + damage + " de daño a " + target.getSymbol() + "!");
        } else {
            System.out.println("❌ Objetivo fuera de alcance del arquero.");
        }
    }
}
//falta penalizacion por ser debil