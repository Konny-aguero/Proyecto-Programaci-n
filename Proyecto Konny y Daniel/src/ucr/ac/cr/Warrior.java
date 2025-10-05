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
        if (Math.abs(newRow - getRow()) <= 1 && Math.abs(newCol - getCol()) <= 1) {
            if (isInsideBoard(newRow, newCol, boardSize)) {
                setRow(newRow);
                setCol(newCol);
                addMove();
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
        addDamageDealt(damage);
        if (!target.isAlive()){
            addKill();
        }


        System.out.println("🏹 Arquero dispara y hace " + damage + " de daño!");
    }
}
