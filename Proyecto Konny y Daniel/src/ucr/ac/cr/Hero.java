package ucr.ac.cr;

public abstract class Hero {
    protected int hp;
    protected int row;
    protected int col;
    protected char symbol;
    protected int damageDealt;
    protected int damageTaken;
    protected int kills;
    protected int moves;

    public Hero(int hp, char symbol) {
        this.hp = hp;
        this.symbol = symbol;
    }

    public abstract boolean move(int newRow, int newCol, int boardSize);
    public abstract void attack(Hero target);

    public void takeDamage(int dmg) {
        this.hp -= dmg;
        if (this.hp < 0) this.hp = 0;
        this.damageTaken += dmg;
    }

    public boolean isAlive() {
        return this.hp > 0;
    }

    public char getSymbol()
    { return symbol; }
    public int getRow()
    { return row; }
    public int getCol()
    { return col; }
    public int getHp()
    { return hp; }

    protected boolean isInsideBoard(int r, int c, int size) {
        return r >= 0 && r < size && c >= 0 && c < size;
    }
}
