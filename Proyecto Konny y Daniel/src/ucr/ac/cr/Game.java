package ucr.ac.cr;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Game {

    private int size;
    private Players player1;
    private Players player2;
    private Hero[] heroes1;
    private Hero[] heroes2;
    private String activeplayer;
    private int turn;
    private boolean activegame;
    private String path;


    public Game(int size, Players player1, Players player2, Hero[] heroes1, Hero[] heroes2, String activeplayer) {
        this.size = size;
        this.player1 = player1;
        this.player2 = player2;
        this.heroes1 = heroes1;
        this.heroes2 = heroes2;
        this.activeplayer = activeplayer;
        this.turn = 1;
        this.activegame= true;

        setPath(player1.getNameArmy() + "-vs-" + player2.getNameArmy() + "-" +
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss")) + ".json");
    }


    public void guardarJson() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            if (getPath() == null || getPath().isEmpty()) {
                setPath(player1.getNameArmy() + "-vs-" + player2.getNameArmy() + "-" +
                        LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss")) + ".json");
            }
            mapper.writeValue(new File(getPath()), this);
            System.out.println("Partida guardada en: " + getPath());
        } catch (Exception e) {
            System.out.println("Error al guardar la partida: " + e.getMessage());
        }
    }



//    /** Carga un juego desde un archivo JSON */
//    public static Game cargarDesdeArchivo(String ruta) {
//        ObjectMapper mapper = new ObjectMapper();
//        try {
//            Game juego = mapper.readValue(new File(ruta), Game.class);
//            System.out.println("✅ Partida cargada desde: " + ruta);
//            return juego;
//        } catch (IOException e) {
//            System.out.println("⚠ Error al cargar la partida: " + e.getMessage());
//            return null;
//        }
//    }
//
//    /** Pasa el turno al siguiente jugador y guarda automáticamente el estado */
//    public void pasarTurno() {
//        turn++;
//        activeplayer = activeplayer.equals(player1.getPlayerName())
//                ? player2.getPlayerName()
//                : player1.getPlayerName();
//        System.out.println("➡ Turno #" + turn + " - Ahora juega: " + activeplayer);
//        guardarEstado();
//    }
//
//    /** Marca el juego como finalizado y guarda estado final */
//    public void finalizarJuego(String ganador) {
//        activegame = false;
//        System.out.println("🏁 El juego ha finalizado. Ganador: " + ganador);
//        guardarEstado();
//    }
//
//    /** Marca que un jugador se ha rendido y guarda */
//    public void rendirse(String jugadorQueSeRinde) {
//        String ganador = jugadorQueSeRinde.equals(player1.getPlayerName())
//                ? player2.getPlayerName()
//                : player1.getPlayerName();
//        System.out.println("🏳 " + jugadorQueSeRinde + " se ha rendido. Gana " + ganador);
//        activegame = false;
//        guardarEstado();
//    }
//
//    // ===================== MÉTODOS AUXILIARES =====================
//
//    /** Genera un nombre único para el archivo de guardado */
//    private String generarRutaArchivo() {
//        String fecha = new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date());
//        String nombre = player1.getNameArmy() + "-vs-" + player2.getNameArmy() + "-" + fecha + ".json";
//        return "partidas/" + nombre;
//    }
//


    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Players getPlayer1() {
        return player1;
    }

    public void setPlayer1(Players player1) {
        this.player1 = player1;
    }

    public Players getPlayer2() {
        return player2;
    }

    public void setPlayer2(Players player2) {
        this.player2 = player2;
    }

    public Hero[] getHeroes1() {
        return heroes1;
    }

    public void setHeroes1(Hero[] heroes1) {
        this.heroes1 = heroes1;
    }

    public Hero[] getHeroes2() {
        return heroes2;
    }

    public void setHeroes2(Hero[] heroes2) {
        this.heroes2 = heroes2;
    }

    public String getActiveplayer() {
        return activeplayer;
    }

    public void setActiveplayer(String activeplayer) {
        this.activeplayer = activeplayer;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public boolean isActivegame() {
        return activegame;
    }

    public void setActivegame(boolean activegame) {
        this.activegame = activegame;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
