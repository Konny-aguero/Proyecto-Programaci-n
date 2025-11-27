package ucr.ac.cr;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


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
    private String pathHtml;
    private String pathHistoryMoves;
    private List<String> movesHistory = new ArrayList<>();

    public Game() {
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
        setPathHtml(player1.getNameArmy() + "-vs-" + player2.getNameArmy() + "-" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss")) + ".html");
        setPathHistoryMoves(player1.getNameArmy() + "-vs-" + player2.getNameArmy()+ "-moves.json");
    }

    public void movesHistory(Command cmd, String player) {
        if (cmd == null) return;
        String entry = "Turno " + this.getTurn() + " - " + player + " - " + cmd.toString();
        movesHistory.add(entry);
        try {
            this.guardarJson();
        } catch (Exception e) {
            System.out.println("No se pudo guardar el JSON"+e.getMessage());
        }

        writeMovesJsonFile();
    }

    public List<String> getMovesHistory() {
        return movesHistory;
    }

    public String getMovesHistoryJson() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            return mapper.writeValueAsString(movesHistory);
        } catch (Exception e) {
            return "[]";
        }
    }

    private void writeMovesJsonFile() {
        String basePath = this.getPath();
        String movesPath;
        if (basePath != null && basePath.endsWith(".json")) {
            movesPath = basePath.replace(".json", "-moves.json");
        } else {
            movesPath = "moves-history.json";
        }
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            mapper.writeValue(new File(movesPath), movesHistory);
        } catch (IOException e) {
            // no destruir ejecución por fallo al escribir el archivo de movimientos
            e.printStackTrace();
        }
    }

    public void guardarJson() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            if (getPath() == null || getPath().isEmpty()) {
                setPath(player1.getNameArmy() + "-vs-" + player2.getNameArmy()+ ".json");
            }
            mapper.writeValue(new File(getPath()), this);
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

    public void exportToHTML() {
        try {
            StringBuilder template = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new FileReader("template.html"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    template.append(line).append("\n");
                }
            }

            StringBuilder posiciones = new StringBuilder();
            posiciones.append("<tr><th>Jugador</th>\n" +
                    "            <th>Tipo</th>\n" +
                    "            <th>HP</th>\n" +
                    "            <th>Fila</th>\n" +
                    "            <th>Columna</th></tr>");

            addStrToHtml(posiciones, heroes1, player1, "p1");
            addStrToHtml(posiciones, heroes2, player2, "p2");

            posiciones.append("</table>");

            StringBuilder stats = new StringBuilder();
            stats.append("<table>");
            stats.append("<tr>" +
                    "<th>Jugador</th>" +
                    "<th>Héroes vivos</th>" +
                    "<th>Daño infligido</th>" +
                    "<th>Daño recibido</th>" +
                    "<th>Bajas</th>" +
                    "</tr>");


            addStrToStats(stats, heroes1, player1, "p1");
            addStrToStats(stats, heroes2, player2, "p2");

            stats.append("</table>");

            String html = template.toString()
                    .replace("{{turno_actual}}", String.valueOf(turn))
                    .replace("{{jugador_1}}", player1.getNameArmy())
                    .replace("{{jugador_2}}", player2.getNameArmy())
                    .replace("{{tabla_posiciones}}", posiciones.toString())
                    .replace("{{tabla_estadisticas}}", stats.toString());



            try (BufferedWriter writer = new BufferedWriter(new FileWriter(getPathHtml()))) {
                writer.write(html);
            }

            } catch (Exception e) {
                System.out.println("Error al exportar la partida: " + e.getMessage());
            }
    }

    private void addStrToStats(StringBuilder stats, Hero[] heroes1, Players player1, String cssClass) {
        for (Hero h : heroes1) {
            if (h != null) {
                stats.append("<tr class='").append(cssClass).append("'>")
                        .append("<td>").append(player1.getNameArmy()).append("</td>")
                        .append("<td>").append(h.getSymbol() == 'A' ? "Arquero" : h.getSymbol() == 'G' ? "Guerrero" : h.getSymbol() == 'M' ? "Mago" : h.getSymbol() == 'T' ? "Tanque" : h.getSymbol() == 'S' ? "Asesino" : "Desconocido")
                        .append("</td>")
                        .append("<td>").append(h.getDamageDealt()).append("</td>")
                        .append("<td>").append(h.getDamageTaken()).append("</td>")
                        .append("<td>").append(h.getKills()).append("</td>")
                        .append("</tr>");
            }
        }
    }

    private void addStrToHtml(StringBuilder posiciones, Hero[] heroes2, Players player2, String cssClass) {
        for (Hero h : heroes2) {
            if (h != null) {
                posiciones.append("<tr class='").append(cssClass).append("'>")
                        .append("<td>").append(player2.getNameArmy()).append("</td>")
                        .append("<td>").append(h.getSymbol() == 'A' ? "Arquero" : h.getSymbol() == 'G' ? "Guerrero" : h.getSymbol() == 'M' ? "Mago" : h.getSymbol() == 'T' ? "Tanque" : h.getSymbol() == 'S' ? "Asesino" : "Desconocido")
                        .append("</td>")
                        .append("<td>").append(h.getHp()).append("</td>")
                        .append("<td>").append(h.getRow()+1).append("</td>")
                        .append("<td>").append(h.getCol()+1).append("</td>")
                        .append("</tr>");
            }
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

    public String getPathHtml() {
        return pathHtml;
    }

    public void setPathHtml(String pathhtml) {
        this.pathHtml = pathhtml;
    }

    public void setMovesHistory(List<String> movesHistory) {
        this.movesHistory = movesHistory;
    }

    public String getPathHistoryMoves() {
        return pathHistoryMoves;
    }

    public void setPathHistoryMoves(String pathHistoryMoves) {
        this.pathHistoryMoves = pathHistoryMoves;
    }
}
