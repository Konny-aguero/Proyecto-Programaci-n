package ucr.ac.cr;

import java.util.Random;

public class Wizard extends Hero {

    public Wizard(int hp, char symbol) {
        super(hp, symbol);
    }

    @Override
    public boolean move(int newRow, int newCol, int boardSize) {
        if (Math.abs(newRow - getRow()) <= 2 && Math.abs(newCol - getCol()) <= 2) {
            if (isInsideBoard(newRow, newCol, boardSize)) {
                setRow(newRow);
                setCol(newCol);
                addMove();
                System.out.println("✨ Mago se teletransportó a (" + newRow + "," + newCol + ")");
                return true;
            }
        }
        System.out.println("Movimiento inválido para el Mago!");
        return false;
    }

    // Ataque en área: casilla objetivo y adyacentes
    public void attackArea(Hero[] heroes, int targetRow, int targetCol, int boardSize) {
        Random rand = new Random();
        for (Hero h : heroes) {
            int r = h.getRow();
            int c = h.getCol();
            if (Math.abs(r - targetRow) <= 1 && Math.abs(c - targetCol) <= 1) {
                int damage = 4 + rand.nextInt(4);
                if (h instanceof Warrior) damage += 2;
                h.takeDamage(damage);
                addDamageDealt(damage);
                if (!h.isAlive()) addKill();
                System.out.println("✨ Mago ataca y hace " + damage + " de daño a " + h.getSymbol() + "!");
            }
        }
    }

    @Override
    public void attack(Hero target) {

        System.out.println("Usa attackArea para el ataque en área del mago.");
    }
}
//no entiendo lo de attackArea
//falta penalizacion por ser debil
