package sim;

public abstract class Entity {
    protected int x, y;
    protected int age;

    public Entity(int x, int y) {
        this.x = x;
        this.y = y;
        this.age = 0;
    }

    public abstract void act(World world);

    public int getX() { return x; }
    public int getY() { return y; }
}
