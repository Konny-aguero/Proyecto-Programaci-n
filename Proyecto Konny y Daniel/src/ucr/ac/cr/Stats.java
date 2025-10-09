package ucr.ac.cr;

public class Stats {

    public static void showStats(Hero hero) {
        System.out.println("=== Estadísticas del héroe ===");
        System.out.println("Tipo: " + hero.getClass().getSimpleName());
        System.out.println("Símbolo: " + hero.getSymbol());
        System.out.println("HP actual: " + hero.getHp());
        System.out.println("Posición: (" + (hero.getRow()+1) + "," + (hero.getCol()+1) + ")");
        System.out.println("Daño infligido: " + hero.getDamageDealt());
        System.out.println("Daño recibido: " + hero.getDamageTaken());
        System.out.println("Movimientos realizados: " + hero.getMoves());
        System.out.println("Enemigos eliminados: " + hero.getKills());
        System.out.println("=============================");
    }

}
