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

        System.out.println("Movimiento inválido para el Tanque!");
        return false;
    }

    @Override
    public void attack(Hero target) {
        int rowDifference = Math.abs(target.getRow() - getRow());
        int colDifference = Math.abs(target.getCol() - getCol());

        if ((rowDifference == 1 && colDifference == 0) || (rowDifference == 0 && colDifference == 1)) {
            Random rand = new Random();
            int damage = 3 + rand.nextInt(2);

            target.takeDamage(damage);
            addDamageDealt(damage);

            if (!target.isAlive()) addKill();

            System.out.println("🛡️ Tanque ataca y hace " + damage + " de daño!");
        } else {
            System.out.println("Objetivo fuera de rango para el Tanque!");
        }
    }

    @Override
    public void takeDamage(int damage) {
        int reduced = damage - 1;
        if (reduced < 0) reduced = 0;
        super.takeDamage(reduced);
    }
}
