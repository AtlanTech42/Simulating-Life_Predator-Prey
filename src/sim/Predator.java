package sim;

public class Predator extends Entity {
    private int energy = 5;

    public Predator(int x, int y) {
        super(x, y);
    }

    @Override
    public void act(World world) {
        // Hunt prey, lose/gain energy
    }

    @Override
    public String toString() {
        return "X";
    }
}
