package ucr.ac.cr;

public abstract class Hero {
    private int hp;

    public Hero(int hp) {
        this.hp = hp;
    }

    public abstract void move();

    public abstract void attack();


}
