package sim;

import java.util.List;
import java.util.Random;

public class Prey extends Entity {
    public Prey(int x, int y) {
        super(x, y);
    }

    @Override
    public void act(World world) {
        age++;

        // Try to move
        List<int[]> moves = world.getAdjacentEmpty(x, y);
        if (!moves.isEmpty()) {
            int[] move = moves.get(new Random().nextInt(moves.size()));
            world.set(x, y, null);
            x = move[0];
            y = move[1];
            world.set(x, y, this);
        }

        // Try to reproduce
        if (age >= 5) {
            List<int[]> spawnSpots = world.getAdjacentEmpty(x, y);
            if (!spawnSpots.isEmpty()) {
                int[] spawn = spawnSpots.get(new Random().nextInt(spawnSpots.size()));
                world.set(spawn[0], spawn[1], new Prey(spawn[0], spawn[1]));
                age = 0; // reset after reproduction
            }
        }
    }

    @Override
    public String toString() {
        return "P";
    }
}
