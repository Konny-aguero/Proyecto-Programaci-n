//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ucr.ac.cr;

import java.util.Random;

public class Killer extends Hero {


    public Killer(int hp, char symbol) {
        super(hp, symbol);
    }

    @Override
    public boolean move(int newRow, int newCol, int boardSize) {
        if (Math.abs(newRow - getRow()) == 2 && Math.abs(newCol - getCol()) == 2) {
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
        int totaldamage = 0;

        for (int i = 0; i<2; i++){
            int damage = 7 + rand.nextInt(3);

            if (target instanceof Archer || target instanceof Wizard){
                damage = damage +3;

            }

            target.takeDamage(damage);
            addDamageDealt(damage);
            totaldamage = totaldamage + damage;

            if (!target.isAlive()){
                addKill();
                break;
            }
        }


        System.out.println("🗡️ Asesino ataca y hace " + totaldamage + " de daño en total!");
    }
}
