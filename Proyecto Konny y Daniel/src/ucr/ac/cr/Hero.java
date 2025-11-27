package ucr.ac.cr;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Archer.class, name = "archer"),
        @JsonSubTypes.Type(value = Warrior.class, name = "warrior"),
        @JsonSubTypes.Type(value = Wizard.class, name = "wizard"),
        @JsonSubTypes.Type(value = Tank.class, name = "tank"),
        @JsonSubTypes.Type(value = Killer.class, name = "killer")
})

public abstract class Hero {
    private int hp;
    private int row;
    private int col;
    private char symbol;
    private int damageDealt;
    private int damageTaken;
    private int kills;
    private int moves;
    private boolean placed = false;

    public Hero(int hp, int row, int col, char symbol, int damageDealt, int damageTaken, int kills, int moves, boolean placed) {
        this.hp = hp;
        this.row = row;
        this.col = col;
        this.symbol = symbol;
        this.damageDealt = damageDealt;
        this.damageTaken = damageTaken;
        this.kills = kills;
        this.moves = moves;
        this.placed = placed;
    }

    public Hero() {
    }

    public boolean isPlaced() {
        return placed;
    }

    public void setPlaced(boolean placed) {
        this.placed = placed;
    }

    public Hero(int hp, char symbol) {
        this.hp = hp;
        this.symbol = symbol;
    }

    public boolean move(int newRow, int newCol, int boardSize) {
        return false;
    }

    public boolean attack(Hero target) {
        return false;
    }

    public void takeDamage(int dmg) {
        this.hp = hp - dmg;
        if (this.hp < 0) this.hp = 0;
        this.damageTaken += dmg;
    }

    public boolean isAlive() {
        return this.hp > 0;
    }

    public int getHp() {
        return hp;
    }
    public int getRow() {
        return row;
    }
    public int getCol() {
        return col;
    }
    public char getSymbol() {
        return symbol;
    }
    public int getDamageDealt() {
        return damageDealt;
    }
    public int getDamageTaken() {
        return damageTaken;
    }
    public int getKills() {
        return kills;
    }
    public int getMoves() {
        return moves;
    }
    public void setRow(int row) {
        this.row = row;
    }
    public void setCol(int col) {
        this.col = col;
    }
    public void addMove() { this.moves++; }
    public void addDamageDealt(int dmg) { this.damageDealt += dmg; }
    public void addKill() { this.kills++; }
    protected boolean isInsideBoard(int r, int c, int size) {
        return r >= 0 && r < size && c >= 0 && c < size;
    }
}
