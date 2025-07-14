package sim;

import java.util.List;
import java.util.Random;

public class Predator extends Entity {
    private int energy = 8;

    public Predator(int x, int y) {
        super(x, y);
    }

    @Override
    public void act(World world) {
        age++;
        energy--;

        // Try to eat prey
        List<int[]> preyNearby = world.getAdjacentPrey(x, y);
        if (!preyNearby.isEmpty()) {
            int[] preyPos = preyNearby.get(new Random().nextInt(preyNearby.size()));
            world.set(x, y, null);  // move out
            x = preyPos[0];
            y = preyPos[1];
            world.set(x, y, this);  // move in, eat prey
            energy += 5;
        } else {
            // Try to move randomly
            List<int[]> moves = world.getAdjacentEmpty(x, y);
            if (!moves.isEmpty()) {
                int[] move = moves.get(new Random().nextInt(moves.size()));
                world.set(x, y, null);
                x = move[0];
                y = move[1];
                world.set(x, y, this);
            }
        }

        // Reproduce if strong
        if (energy >= 10) {
            List<int[]> spots = world.getAdjacentEmpty(x, y);
            if (!spots.isEmpty()) {
                int[] spawn = spots.get(new Random().nextInt(spots.size()));
                world.set(spawn[0], spawn[1], new Predator(spawn[0], spawn[1]));
                energy -= 5; // cost to reproduce
            }
        }

        // Die if starved
        if (energy <= 0) {
            world.set(x, y, null);
        }
    }

    @Override
    public String toString() {
        return "X";
    }
}
