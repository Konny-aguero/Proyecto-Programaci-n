package ucr.ac.cr;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;


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
    public Game(){
    }

    public Game(int size, Players player1, Players player2, Hero[] heroes1, Hero[] heroes2, String activeplayer) {
        this.size = size;
        this.player1 = player1;
        this.player2 = player2;
        this.heroes1 = heroes1;
        this.heroes2 = heroes2;
        this.activeplayer = activeplayer;
        this.turn = 1;
        this.activegame= true;
        setPath(player1.getNameArmy() + "-vs-" + player2.getNameArmy()+ ".json");
    }

    public void guardarJson() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            if (getPath() == null || getPath().isEmpty()) {
                setPath(player1.getNameArmy() + "-vs-" + player2.getNameArmy()+ ".json");
            }
            mapper.writeValue(new File(getPath()), this);
            System.out.println("Partida guardada en: " + getPath());
        } catch (Exception e) {
            System.out.println("Error al guardar la partida: " + e.getMessage());
        }
    }

    public static Game loadFromFile(String ruta) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Game game = mapper.readValue(new File(ruta), Game.class);
            if (game == null) {
                System.out.println("Archivo inválido o vacío: ");
                return null;
            }
            System.out.println("Partida cargada desde: " + ruta);
            return game;
        } catch (Exception e) {
            System.out.println("Error al cargar la partida: " + e.getMessage());
            return null;
        }
    }

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
