package ucr.ac.cr;

public abstract class Hero {
    private int hp;
    private String name;

    public Hero(String name) {
        this.name = name;
    }

    public String getShortName() {
        return name;
    }

    public Hero(int hp) {
        this.hp = hp;
    }

    public abstract void move();

    public abstract void attack();


}
