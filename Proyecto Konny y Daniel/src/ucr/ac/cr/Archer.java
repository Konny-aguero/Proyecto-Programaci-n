package ucr.ac.cr;

import java.util.Random;

public class Archer extends Hero {


    public Archer(int hp, char symbol) {
        super(hp, symbol);
    }

    @Override
    public boolean move(int newRow, int newCol, int boardSize) {
        if (Math.abs(newRow - row) <= 1 && Math.abs(newCol - col) <= 1) {
            if (isInsideBoard(newRow, newCol, boardSize)) {
                row = newRow;
                col = newCol;
                moves++;
                return true;
            }
        }
        return false;
    }

    @Override
    public void attack(Hero target) {
        Random rand = new Random();
        int damage = 5 + rand.nextInt(3);

        if (target instanceof Tank) {
            damage = damage + 2;
        }

         target.takeDamage(damage);
        this.damageDealt = damageDealt + damage;
        if (!target.isAlive()) kills++;

        System.out.println("🏹 Arquero dispara y hace " + damage + " de daño!");
    }
}
