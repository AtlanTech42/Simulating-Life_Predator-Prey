package sim;

public class Prey extends Entity {
    public Prey(int x, int y) {
        super(x, y);
    }

    @Override
    public void act(World world) {
        // Move randomly, maybe reproduce
    }

    @Override
    public String toString() {
        return "P";
    }
}
