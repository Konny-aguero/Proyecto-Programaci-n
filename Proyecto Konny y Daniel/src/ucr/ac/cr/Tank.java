//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ucr.ac.cr;

import java.util.Random;

public class Tank extends Hero {
    public Tank(int hp) {
        super(hp, 'T');
    }

    @Override
    public boolean move(int newRow, int newCol, int boardSize) {
        // El arquero puede mover 1 casilla en cualquier dirección
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
        int damage = 5 + rand.nextInt(3); // 5 + rand(0–2)

        if (target instanceof Tank) {
            damage = damage + 2; // ventaja contra tanques
        }

        target.takeDamage(damage);
        this.damageDealt += damage;
        if (!target.isAlive()) kills++;

        System.out.println("🏹 Arquero dispara y hace " + damage + " de daño!");
    }
}

