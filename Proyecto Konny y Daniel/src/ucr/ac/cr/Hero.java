package ucr.ac.cr;
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

    public abstract boolean move(int newRow, int newCol, int boardSize);
    public abstract boolean attack(Hero target);

    public int[][] validMoves(int boardSize) {
        return new int[0][0];
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
