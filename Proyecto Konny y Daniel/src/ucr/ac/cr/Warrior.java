//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

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
                System.out.println("⚔️ Guerrero se movió a (" + newRow + "," + newCol + ")");
                return true;
            }
        }

        System.out.println("Movimiento inválido para el Guerrero!");
        return false;
    }

    @Override
    public void attack(Hero target) {
        int rowDifference = Math.abs(target.getRow() - getRow());
        int colDifference = Math.abs(target.getCol() - getCol());

        if ((rowDifference == 1 && colDifference == 0) || (rowDifference == 0 && colDifference == 1)) {
            Random rand = new Random();
            int damage = 6 + rand.nextInt(3);

            target.takeDamage(damage);
            addDamageDealt(damage);

            if (!target.isAlive()) addKill();

            System.out.println("⚔️ Guerrero ataca y hace " + damage + " de daño!");
        } else {
            System.out.println("Objetivo fuera de rango para el Guerrero!");
        }
    }
}
